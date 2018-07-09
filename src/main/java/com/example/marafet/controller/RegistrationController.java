package com.example.marafet.controller;

import com.example.marafet.model.User;
import com.example.marafet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){

        if(StringUtils.isEmpty(user.getUsername()) || user.getEmail().equals("") || user.getPassword().equals("")){
            model.addAttribute("message", "Заполните все поля для регистрации");
            return "registration";
        }
        if(!userService.addUser(user)){
            model.addAttribute("message", "Такой пользователь уже зарегистрирован");
            return "registration";
        }
        else{
            model.addAttribute("message", "");
        }


        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){

        boolean isActivated = userService.activateUser(code);

        if (isActivated){
            model.addAttribute("message", "Пользователь активирован");
        }
        else{
            model.addAttribute("message", "Ошибка активации");
        }
        return "login";
    }
}
