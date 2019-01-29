package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.dto.AppUserDto;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trener/")
public class TrenerUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/protegelist")
    public String getProtegeList(@RequestParam(name = "username", required = false) String usernameSearched, Model model) {


        List<AppUser> appUsers;
        if (usernameSearched == null) {
            appUsers = appUserService.getAllUsers();
        } else {
            appUsers = appUserService.searchUsername(usernameSearched);
        }

        model.addAttribute("protege_list", appUsers);
        return "trener/protegelist";
    }

    @GetMapping("/addprotege/{id}")
    public String addProtege(@PathVariable Long id) {
        appUserService.addProtegeToList(id);

        return "redirect:/trener/prote,m nbvgelist";
    }
}
