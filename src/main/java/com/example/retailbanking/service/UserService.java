package com.example.retailbanking.service;

import com.example.retailbanking.model.User;

public interface UserService {
    User findByUsername(String username);
}
