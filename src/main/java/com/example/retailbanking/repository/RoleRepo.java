package com.example.retailbanking.repository;

import com.example.retailbanking.model.Account;
import com.example.retailbanking.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
}
