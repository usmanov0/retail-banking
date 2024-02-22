package com.example.retailbanking.service.impl;

import com.example.retailbanking.model.*;
import com.example.retailbanking.repository.*;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
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
    public void betweenAccountsTransfer(String transferFrom, String transferTo, Double amount, Account account, SavingsAccount savingsAccount) throws InsufficientResourcesException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        User user = userService.findByUsername(transferFrom);
        if (user == null) throw  new IllegalArgumentException("Invalid source account");
        Account sourceAccount = user.getAccount();
        SavingsAccount sourceSavingsAccount = user.getSavingsAccount();

        if (account != null && account.equals(sourceAccount)){
            if (amount > sourceAccount.getAccountBalance().doubleValue()){
                throw new InsufficientResourcesException("Insufficient funds in the source account");
            }
            sourceAccount.setAccountBalance(sourceAccount.getAccountBalance().subtract(BigDecimal.valueOf(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(BigDecimal.valueOf(amount)));

            Transaction withdrawalTransaction = new Transaction(new Date(), "Transfer to savings account",
                    "Account", "Finished", amount, sourceAccount.getAccountBalance(), sourceAccount);
            Transaction depositTransaction = new Transaction(new Date(), "Transfer from Current Account",
                    "Account", "Finished", amount, savingsAccount.getAccountBalance(), account);
            transactionRepo.save(withdrawalTransaction);
            transactionRepo.save(depositTransaction);
        }else if (savingsAccount != null && savingsAccount.equals(sourceSavingsAccount)){
            if (amount > sourceSavingsAccount.getAccountBalance().doubleValue()){
                throw new InsufficientResourcesException("Insufficient funds in the source account.");
            }
            sourceSavingsAccount.setAccountBalance(sourceSavingsAccount.getAccountBalance().subtract(BigDecimal.valueOf(amount)));
            account.setAccountBalance(account.getAccountBalance().add(BigDecimal.valueOf(amount)));

            SavingsTransaction withdrawalTransaction = new SavingsTransaction(new Date(), "Transfer to Current Account",
                    "Account", "Finished", amount, sourceSavingsAccount.getAccountBalance(), sourceSavingsAccount);
            SavingsTransaction depositTransaction = new SavingsTransaction(new Date(), "Transfer from Savings Account",
                    "Account", "Finished", amount, account.getAccountBalance(), savingsAccount);
            savingsTransactionRepo.save(withdrawalTransaction);
            savingsTransactionRepo.save(depositTransaction);
        }else {
            throw new IllegalArgumentException("Invalid source account for transfer");
        }

        accountRepo.save(sourceAccount);
        savingsAccountRepo.save(sourceSavingsAccount);
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
