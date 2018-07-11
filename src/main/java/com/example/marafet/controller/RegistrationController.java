package com.example.marafet.controller;

import com.example.marafet.model.User;
import com.example.marafet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          Model model){
        if(!StringUtils.isEmpty(user.getPassword()) && !user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwordError", "Вы указали разные пароли");
        }

        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
//            model.addAttribute("user", user);
            return "registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("usernameError", "Такой пользователь уже зарегистрирован");
            return "registration";
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
