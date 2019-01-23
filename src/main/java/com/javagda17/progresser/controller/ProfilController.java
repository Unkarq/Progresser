package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/profil/")
public class ProfilController {
@Autowired
private AppUserService appUserService;


    @GetMapping("/profil")
    public String getProfilPage() {
        return "profil";
    }

    @RequestMapping(value = "/edit/{identifier}", method = RequestMethod.GET)
    public String getEditForm(Model model, @PathVariable(name = "identifier") Long id) {
        Optional<AppUser> appUserOptional = appUserService.getAppUserById(id);
        if (appUserOptional.isPresent()) {
            model.addAttribute("tweetToEdit", appUserOptional.get());
            model.addAttribute("wpisyList",appUserService.getAllInfo());

            return "item";
        }

        return "redirect:/checklist";
    }


    @RequestMapping(value = "/profil/{identifier}", method = POST)
    public String getEditForm(Model model, @PathVariable(name = "identifier") Long id,
                              AppUser appUser) {
        appUserService.updateAppUser(appUser);

        return "redirect:/profil";
    }

    @GetMapping("/removeProfil")
    public String removeFromProfil(@RequestParam(name = "appUserId") Long appUserid){
        appUserService.remove(appUserid);
        return "/pr";
    }

}