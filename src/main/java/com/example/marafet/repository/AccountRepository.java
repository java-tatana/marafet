package com.example.marafet.repository;

import com.example.marafet.model.Account;
import com.example.marafet.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(User user);
    List<Account> findByCurrencyAndUser (String currency, User user);
}
