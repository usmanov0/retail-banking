package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.*;
import com.example.retailbanking.repository.AccountRepo;
import com.example.retailbanking.repository.SavingsAccountRepo;
import com.example.retailbanking.service.AccountService;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;
    private SavingsAccountRepo savingsAccountRepo;
    private UserService userService;
    private TransactionService transactionService;
    @Override
    public Account createCurrentAccount() {
        Account account = new Account();
        account.setAccountBalance(new BigDecimal(0.0));
        account.setAccountNumber(accountGen());

        accountRepo.save(account);

        return accountRepo.findByAccountNumber(account.getAccountNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());
        savingsAccountRepo.save(savingsAccount);
        return savingsAccountRepo.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    @Override
    public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Current")){
            Account account = user.getAccount();
            account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));
            accountRepo.save(account);

            Date date = new Date();

            Transaction transaction = new Transaction(date,"Deposit to current Account","Account", "Finished",amount,account.getAccountBalance(),account);
            transactionService.saveCurrentDepositTransaction(transaction);
        }else if (accountType.equalsIgnoreCase("Savings")){
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountRepo.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Deposit to savings account", "Account","Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }

    @Override
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Current")){
            Account account = user.getAccount();
            account.setAccountBalance(account.getAccountBalance().subtract(new BigDecimal(amount)));
            accountRepo.save(account);

            Date date = new Date();

            Transaction transaction = new Transaction(date, "WithDraw from to current account", "Account","Finished",amount,account.getAccountBalance(), account);
            transactionService.saveCurrentWithDrawTransaction(transaction);
        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountRepo.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "WithDraw from to savings account", "Account", "Finished", amount,savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsWithDrawTransaction(savingsTransaction);
        }
    }

    private int accountGen() {
        return ThreadLocalRandom.current().nextInt(1000, 1987654321);
    }
}
