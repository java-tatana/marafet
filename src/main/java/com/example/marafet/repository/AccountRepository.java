package com.example.marafet.repository;

import com.example.marafet.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(String user);
}
