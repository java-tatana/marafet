package com.example.marafet.controller;

import com.example.marafet.model.Account;
import com.example.marafet.model.Transaction;
import com.example.marafet.model.User;
import com.example.marafet.repository.AccountRepository;
import com.example.marafet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("{account}")
    public String getList(@AuthenticationPrincipal User user,
                          @PathVariable Account account,
                          Model model){
        Iterable<Transaction> transactions = account.getTransactionList();
        model.addAttribute("transactions", transactions);
        return "/transactions";
    }

    @PostMapping("{account}")
    public String addTransaction (@AuthenticationPrincipal User user,
                                  @PathVariable Account account,
                                  @RequestParam String description, @RequestParam int sum,
                                  @RequestParam String date,
                                  Model model){
        Transaction transaction = new Transaction(date, sum, description, account);
        transactionRepository.save(transaction);
        Iterable<Transaction> transactions = account.getTransactionList();
        model.addAttribute("transactions", transactions);
        return "/transactions";
    }

}
