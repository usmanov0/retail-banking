package com.example.retailbanking.repository;

import com.example.retailbanking.model.Account;
import com.example.retailbanking.model.SavingsAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {
    Account findByAccountNumber(int accountNumber);
}
