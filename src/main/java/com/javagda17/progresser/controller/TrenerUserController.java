package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/trener/")
public class TrenerUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/protegelist")
    public String getProtegeList(Model model){
        List<AppUser> protegeslist = appUserService.getAllProteges();
        model.addAttribute("protege_list", protegeslist);
        return "trener/protegelist";


    }


}
