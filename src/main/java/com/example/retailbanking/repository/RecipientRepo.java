package com.example.retailbanking.repository;

import com.example.retailbanking.model.Recipient;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface RecipientRepo extends CrudRepository<Recipient, Long> {
    Recipient findByName(String recipientName);

    void deleteByName(String recipientName);
}
