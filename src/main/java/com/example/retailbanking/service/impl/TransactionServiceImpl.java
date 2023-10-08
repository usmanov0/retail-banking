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

    }

    @Override
    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {

    }

    @Override
    public void saveCurrentWithDrawTransaction(Transaction transaction) {

    }

    @Override
    public void saveSavingsWithDrawTransaction(SavingsTransaction savingsTransaction) {

    }

    @Override
    public List<Transaction> findTransactionList(String name) {
        return null;
    }

    @Override
    public List<SavingsTransaction> findSavingTransactionList(String name) {
        return null;
    }

    @Override
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, Account account, SavingsAccount savingsAccount) {

    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        return null;
    }

    @Override
    public void saveRecipient(Recipient recipient) {

    }

    @Override
    public Recipient findRecipientByName(String recipientName) {
        return null;
    }

    @Override
    public void deleteRecipientByName(String recipientName) {

    }
}
