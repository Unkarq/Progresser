package com.javagda17.progresser.controller;

import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    @Autowired
    private AppUserService appUserService;


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String submitRegisterForm(String username,
                                     String password,
                                     String passwordConfirm,
                                     String email,
                                     boolean trener) {
        if(!password.equals(passwordConfirm)){
            return "redirect:/register?error=Passwords do not match";
        }
        boolean result = appUserService.tryRegister(username,password,email,trener);
        return "redirect:/login";
    }

   @GetMapping("/profil")
   public String getProfil(){return "profil";}

}
