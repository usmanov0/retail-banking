package com.example.retailbanking.repository;

import com.example.retailbanking.model.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountRepo extends CrudRepository<SavingsAccount, Long> {
}
