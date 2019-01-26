package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.Gender;
import com.javagda17.progresser.model.Specialization;
import com.javagda17.progresser.service.AppUserService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
   @PreAuthorize("isAuthenticated()")
   public String getProfil(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AppUser> appUser = appUserService.findByUsername(user.getUsername());
        AppUser optionaAppuser = appUser.get();
        model.addAttribute("email", optionaAppuser.getEmail());
        model.addAttribute("name", optionaAppuser.getName());
        model.addAttribute("phoneNumber", optionaAppuser.getPhoneNumber());
        model.addAttribute("surname", optionaAppuser.getSurname());
        model.addAttribute("gender", optionaAppuser.getGender());
        model.addAttribute("spec", optionaAppuser.getSpecialization());
        model.addAttribute("city", optionaAppuser.getCity());
        model.addAttribute("role", optionaAppuser.getUserRoles());
        model.addAttribute("creationtime",optionaAppuser.getCreationDate());
        model.addAttribute("username",optionaAppuser.getUsername());
        return "profil";}

}
