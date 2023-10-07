package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.User;
import com.example.retailbanking.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSecurityServiceImpl implements UserDetailsService {
    private static final Logger  logger = LoggerFactory.getLogger(UserSecurityServiceImpl.class);
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null){
            logger.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username "+ username+" not found");
        }
        return user;
    }
}
