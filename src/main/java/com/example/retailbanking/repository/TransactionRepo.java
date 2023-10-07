package com.example.retailbanking.repository;

import com.example.retailbanking.model.Transaction;
import com.example.retailbanking.security.Authority;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}
