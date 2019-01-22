package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/")
public class AdminUserController {

    @Autowired
    private AppUserService appUserService;


    //admin/users
    @GetMapping("/users")
    public String getUserList(Model model) {
        List<AppUser> users = appUserService.getAllUsers();

        model.addAttribute("user_list", users);
        model.addAttribute("user_roles", users.stream()
                .map(user -> user.getUserRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
                .map(String::valueOf)
                .collect(Collectors.toList()));


        //admin bo plick html znajdue sie w katalogu admin
        return "admin/userlist";

    }
}
