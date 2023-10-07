package com.example.retailbanking.service;

import com.example.retailbanking.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);
}
