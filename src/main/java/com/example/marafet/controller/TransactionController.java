package com.example.marafet.controller;

import com.example.marafet.model.Account;
import com.example.marafet.model.Transaction;
import com.example.marafet.model.User;
import com.example.marafet.repository.AccountRepository;
import com.example.marafet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/transactions")
    public String getList(@AuthenticationPrincipal User user,
                          @RequestParam("id") Long id,
                          Map<String, Object> model){
        Optional<Account> account = accountRepository.findById(id);
        Iterable<Transaction> transactions = account.orElse(null).getTransactionList();
        model.put("transactions", transactions);
        return "transactions";
    }

    @PostMapping("addTransaction")
    public String addTransaction (@AuthenticationPrincipal User user,
                                  @RequestParam("id") Long id,
                                  @RequestParam String description, @RequestParam int sum,
                                  @RequestParam String date,
                                  Map<String, Object> model){
        Optional<Account> account = accountRepository.findById(id);
        Transaction transaction = new Transaction(date, sum, description, account.orElseThrow(NullPointerException::new));
        transactionRepository.save(transaction);
        Iterable<Transaction> transactions = transactionRepository.findAll();
        model.put("transactions", transactions);
        return "/transactions";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       LocalDateTime.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

}
