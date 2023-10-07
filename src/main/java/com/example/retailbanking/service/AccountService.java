package com.example.retailbanking.service;

import com.example.retailbanking.model.Account;
import com.example.retailbanking.model.SavingsAccount;
import java.security.Principal;

public interface AccountService {
    Account createCurrentAccount();
    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
}
