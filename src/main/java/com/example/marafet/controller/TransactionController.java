package com.example.marafet.controller;

import com.example.marafet.model.Account;
import com.example.marafet.model.Transaction;
import com.example.marafet.model.User;
import com.example.marafet.repository.AccountRepository;
import com.example.marafet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
        return "/transactions";
    }

    @PostMapping("{account}")
    public String addTransaction (@AuthenticationPrincipal User user,
                                  @PathVariable Account account,
                                  @RequestParam String description,
                                  @RequestParam int sum,
                                  @RequestParam String date,
                                  @RequestParam("file") MultipartFile file,
                                  Model model) throws IOException {
        Transaction transaction = new Transaction(date, sum, description, account);

        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            transaction.setFilename(resultFilename);
        }
        transactionRepository.save(transaction);
        Iterable<Transaction> transactions = account.getTransactionList();
        model.addAttribute("transactions", transactions);
        return "/transactions";
    }

}
