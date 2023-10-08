package com.example.retailbanking.service;

import com.example.retailbanking.model.User;
import com.example.retailbanking.security.UserRole;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findUserList();

    User saveUser(User user);

    boolean checkUserExists(String username, String email);

    boolean checkUserNameExists(String username);

    public boolean checkEmailExists(String email);

    void createUser(User user, Set<UserRole> userRoles);
}
