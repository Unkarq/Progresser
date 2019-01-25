package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.Gender;
import com.javagda17.progresser.model.Specialization;
import com.javagda17.progresser.service.AppUserService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getRegisterPage(Model model) {
    model.addAttribute("TypGender",Gender.values());
    model.addAttribute("TypSpec",Specialization.values());
        return "register";
    }

    @PostMapping("/register")
    public String submitRegisterForm(
                                     String username,
                                     String password,
                                     String passwordConfirm,
                                     String name,
                                     String surname,
                                     String email,
                                     Gender gender,
                                     Specialization specialization,
                                     String city,
                                     String phonenumber,
                                     boolean trener) {



        if(!password.equals(passwordConfirm)){
            return "redirect:/register?error=Passwords do not match";
        }
        boolean result = appUserService.tryRegister(username,password,email,name,surname,email,gender,specialization,city,phonenumber,trener);
        return "redirect:/login";
    }

   @GetMapping("/profil")
   public String getProfil(){return "profil";}

}
