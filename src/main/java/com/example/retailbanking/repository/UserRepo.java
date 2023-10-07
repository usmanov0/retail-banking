package com.example.retailbanking.repository;

import com.example.retailbanking.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
