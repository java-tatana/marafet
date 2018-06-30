package com.example.marafet.controller;

import com.example.marafet.model.Account;
import com.example.marafet.model.User;
import com.example.marafet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Account> accounts = accountRepository.findAll();
        model.put("accounts", accounts);
        return "main";
    }

    @PostMapping("add")
    public String addAccount(@AuthenticationPrincipal User user,
            @RequestParam String currency, @RequestParam long sum, Map<String, Object> model){
        Account account = new Account(sum, currency, user);
        accountRepository.save(account);
        Iterable<Account> accounts = accountRepository.findAll();
        model.put("accounts", accounts);
        return "main";
    }

    @PostMapping ("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Account> accounts;

        if (filter != null && !filter.isEmpty()) {
           accounts = accountRepository.findByUser(filter);
        }
        else{
            accounts = accountRepository.findAll();
        }
        model.put("accounts", accounts);
        return "main";
    }

}