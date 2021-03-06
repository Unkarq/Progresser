package com.javagda17.progresser.controller;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.Gender;
import com.javagda17.progresser.model.Specialization;
import com.javagda17.progresser.model.dto.AppUserUpdateRequestDto;
import com.javagda17.progresser.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


@Controller

public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/profil")
    @PreAuthorize("isAuthenticated()")
    public String getProfil(Model model) {
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
        model.addAttribute("creationtime", optionaAppuser.getCreationDate());
        model.addAttribute("username", optionaAppuser.getUsername());
        return "profil";
    }


    @GetMapping("/editProfil")
    public String getProfilFormToEdid(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AppUser> appUser = appUserService.findByUsername(user.getUsername());
        AppUser editedUser = appUser.get();

        AppUserUpdateRequestDto dto = new AppUserUpdateRequestDto();
        dto.setIdUserEdited(editedUser.getId());
        dto.setNameUserEdited(editedUser.getName());
        dto.setSurnameUserEdited(editedUser.getSurname());

        dto.setEmailUserEdited(editedUser.getEmail());

        dto.setCityUserEdited(editedUser.getCity());
        dto.setPhonenumberUserEdited(editedUser.getPhoneNumber());

        model.addAttribute("editedUser", dto);

        return "editProfil";
    }

    @PostMapping("/editProfil")
    public String updateProfil(AppUserUpdateRequestDto dto) {
        appUserService.updateProfil(dto);

        return "redirect:/profil";
    }


}





