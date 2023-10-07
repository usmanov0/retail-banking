package com.example.retailbanking.repository;

import com.example.retailbanking.security.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepo extends CrudRepository<Authority, Long> {
}
