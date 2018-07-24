package com.example.marafet.controller;

import com.example.marafet.model.*;
import com.example.marafet.repository.AccountRepository;
import com.example.marafet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Value("${upload.path}")
    private String uploadPath;

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
        model.addAttribute("categories", Category.values());
        model.addAttribute("types", TransType.values());
        return "/transactions";
    }

    @PostMapping("{account}")
    public String addTransaction (@AuthenticationPrincipal User user,
                                  @PathVariable Account account,
                                  @RequestParam String description,
                                  @RequestParam int sum,
                                  @RequestParam String date,
                                  @RequestParam String category,
                                  @RequestParam String type,
                                  Model model) throws IOException {

        Category categoryE = Category.valueOf(category);
        TransType typeE = TransType.valueOf(type);
        Transaction transaction = new Transaction(date.substring(0,10), sum, description, account, typeE);

        transaction.setCategory(Collections.singleton(categoryE));

//        if (file != null && !file.getOriginalFilename().isEmpty()){
//            File uploadDir = new File(uploadPath);
//
//            if(!uploadDir.exists()){
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//            transaction.setFilename(resultFilename);
//        }
        transactionRepository.save(transaction);
        Iterable<Transaction> transactions = account.getTransactionList();
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", Category.values());
        model.addAttribute("types", TransType.values());
        return "/transactions";
    }

}
