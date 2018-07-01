package com.example.marafet.repository;

import com.example.marafet.model.Account;
import com.example.marafet.model.Transaction;
import com.example.marafet.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    List<Transaction> findByAccount (Account account);
}
