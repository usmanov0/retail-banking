package com.example.retailbanking.service;

import com.example.retailbanking.model.*;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service("userDetailsService")
public interface TransactionService {
    void saveCurrentDepositTransaction(Transaction transaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void saveCurrentWithDrawTransaction(Transaction transaction);

    void saveSavingsWithDrawTransaction(SavingsTransaction savingsTransaction);

    List<Transaction> findTransactionList(String name);

    List<SavingsTransaction> findSavingTransactionList(String name);

    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, Account account, SavingsAccount savingsAccount);

    List<Recipient> findRecipientList(Principal principal);

    void saveRecipient(Recipient recipient);

    Recipient findRecipientByName(String recipientName);

    void deleteRecipientByName(String recipientName);
}
