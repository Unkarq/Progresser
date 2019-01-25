package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.Gender;
import com.javagda17.progresser.model.Specialization;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/profil")
public class ProfilController {
    @Autowired
    private AppUserService appUserService;
    @GetMapping(value = "/form/{identifier}")
    public String getProfilFormPage(Model model,@PathVariable(name = "identifier") Long id) {
        Optional<AppUser> optionalAppUser = appUserService.getAppUserById(id);
        if (optionalAppUser.isPresent()) {
            model.addAttribute("curentUser", optionalAppUser.get());
            model.addAttribute("TypGender", Gender.values());

            return "profilForm";
        }
        return "redirect:/profil";

    }

    @PostMapping("/form")
    public String submitEditedProfilForm(String appuserName,
                                         String appUserSurname,
                                         Gender gender,
                                         String email,
                                         Specialization specialization,
                                         String appUserCity,
                                         String appUserPhone
    ) {


        appUserService.updateAppUser(appuserName, appUserSurname, gender, email, specialization, appUserCity, appUserPhone);
        return "redirect:/profil";

    }



    @GetMapping("/profil")
    public String getProfilPage() {
        return "profil";
    }

    @RequestMapping(value = "/profil/{identifier}", method = RequestMethod.GET)
    public String getEditForm(Model model, @PathVariable(name = "identifier") Long id) {
        Optional<AppUser> appUserOptional = appUserService.getAppUserById(id);
        if (appUserOptional.isPresent()) {
            model.addAttribute("editAppUser", appUserOptional.get());


            return "profilForm";
        }

        return "redirect:/";


    }


    @RequestMapping(value = "/profill/{identifier}", method = POST)
    public String getEditForm(Model model, @PathVariable(name = "identifier") Long id,
                              AppUser appUser) {
        appUserService.updateAppUser(appUser);

        return "redirect:/profil";
    }

    @GetMapping("/removeProfil")
    public String removeFromProfil(@RequestParam(name = "appUserId") Long appUserid) {
        appUserService.remove(appUserid);
        return "/pr";
    }




}