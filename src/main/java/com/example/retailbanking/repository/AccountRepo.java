package com.example.retailbanking.repository;

import com.example.retailbanking.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {
}
