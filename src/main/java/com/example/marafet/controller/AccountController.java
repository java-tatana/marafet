package com.example.marafet.controller;

import com.example.marafet.model.Account;
import com.example.marafet.model.User;
import com.example.marafet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

@Controller
@Transactional
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal User user,
                       @RequestParam(required = false, defaultValue = "") String filter,
                       Model model){
        Iterable<Account> accounts;

        if (filter != null && !filter.isEmpty()) {
            accounts = accountRepository.findByCurrencyAndUser(filter, user);
        }
        else{
            accounts = accountRepository.findByUser(user);
        }

        model.addAttribute("accounts", accounts);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("addAccount")
    public String addAccount(@AuthenticationPrincipal User user,
            @RequestParam String currency, @RequestParam long sum, Model model){
        Account account = new Account(sum, currency, user);
        accountRepository.save(account);
        Iterable<Account> accounts = accountRepository.findByUser(user);
        model.addAttribute("accounts", accounts);
        return "main";
    }

    @GetMapping ("/main/{account}")
    public String delete(@PathVariable Account account,
                         @AuthenticationPrincipal User user,
                         Model model){
        accountRepository.delete(account);
        model.addAttribute("accounts", accountRepository.findByUser(user));
        return "redirect:/main";
    }



}
