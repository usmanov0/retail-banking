package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.*;
import com.example.retailbanking.repository.*;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private UserService userService;
    private TransactionRepo transactionRepo;
    private SavingsTransactionRepo savingsTransactionRepo;
    private AccountRepo accountRepo;
    private SavingsAccountRepo savingsAccountRepo;
    private RecipientRepo recipientRepo;




    @Override
    public void saveCurrentDepositTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepo.save(savingsTransaction);
    }

    @Override
    public void saveCurrentWithDrawTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    @Override
    public void saveSavingsWithDrawTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionRepo.save(savingsTransaction);
    }

    @Override
    public List<Transaction> findTransactionList(String name) {
        User user = userService.findByUsername(name);
        List<Transaction> transactionList = user.getAccount().getTransactionList();

        return transactionList;
    }

    @Override
    public List<SavingsTransaction> findSavingTransactionList(String name) {
        User user = userService.findByUsername(name);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionsList();

        return savingsTransactionList;
    }

    @Override
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, Account account, SavingsAccount savingsAccount) {

    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        return (List<Recipient>) recipientRepo.findByName(String.valueOf(principal));
    }

    @Override
    public Recipient saveRecipient(Recipient recipient) {
        return recipientRepo.save(recipient);
    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return recipientRepo.findByName(recipientName);
    }

    @Override
    public void deleteRecipientByName(String recipientName) {
        recipientRepo.deleteByName(recipientName);
    }
}
