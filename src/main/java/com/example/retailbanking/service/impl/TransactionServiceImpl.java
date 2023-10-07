package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.SavingsTransaction;
import com.example.retailbanking.model.Transaction;
import com.example.retailbanking.repository.*;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.stereotype.Service;

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
}
