package com.example.retailbanking.controller;

import com.example.retailbanking.repository.RoleRepo;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private TransactionService transactionService;
    private RoleRepo roleRepo;
}
