package com.example.retailbanking.service;

import com.example.retailbanking.model.SavingsTransaction;
import com.example.retailbanking.model.Transaction;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public interface TransactionService {
    void saveCurrentDepositTransaction(Transaction transaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void saveCurrentWithDrawTransaction(Transaction transaction);

    void saveSavingsWithDrawTransaction(SavingsTransaction savingsTransaction);
}