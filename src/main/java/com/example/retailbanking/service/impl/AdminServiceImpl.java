package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.User;
import com.example.retailbanking.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean deactivateAccount(String username) {
        boolean status = false;
        return status;
    }
}
