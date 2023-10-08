package com.example.retailbanking.repository;

import com.example.retailbanking.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
    List<User> findAll();
}
