package com.example.retailbanking.repository;

import com.example.retailbanking.model.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

public interface SavingsTransactionRepo extends CrudRepository<SavingsTransaction, Long> {
}
