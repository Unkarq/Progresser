package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class AppUserController {

    @Autowired
    private AppUserService appUserService;


    @GetMapping(path = "/get")


    @PostMapping("profilForm")
    public ResponseEntity update(@RequestBody AppUser appUser) {
        if (appUser.getId() != null) { // zabezpieczenie anty id-null
            if (appUserService.updateAppUser(appUser)) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }


}
