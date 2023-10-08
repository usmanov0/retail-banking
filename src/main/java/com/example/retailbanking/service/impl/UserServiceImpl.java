package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.User;
import com.example.retailbanking.repository.UserRepo;
import com.example.retailbanking.security.UserRole;
import com.example.retailbanking.service.UserService;
import lombok.Data;

import java.util.List;
import java.util.Set;


public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> findUserList() {
        return  userRepo.findAll();
    }

    @Override
    public User saveUser(User user) {
        return  userRepo.save(user);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        return checkUserNameExists(username) || checkEmailExists(email);
    }

    @Override
    public boolean checkUserNameExists(String username) {

        return null != findByUsername(username);
    }

    @Override
    public boolean checkEmailExists(String email) {
        
        return null != findByEmail(email);
    }



    @Override
    public void createUser(User user, Set<UserRole> userRoles) {

    }
}
