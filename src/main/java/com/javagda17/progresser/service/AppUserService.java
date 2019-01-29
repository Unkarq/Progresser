package com.javagda17.progresser.service;

import com.javagda17.progresser.model.*;
import com.javagda17.progresser.model.dto.AppUserDto;
import com.javagda17.progresser.model.dto.AppUserUpdateRequestDto;
import com.javagda17.progresser.repository.AppUserRepository;
import com.javagda17.progresser.repository.ProtegeListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private ProtegeListRepository protegeListRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean tryRegister(String username,
                               String password,
                               String passwordConfirm,
                               String name,
                               String Surname,
                               String email,
                               Gender gender,
                               Specialization specialization,
                               String city,
                               String phonenumber,
                               boolean trener) {


        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setEmail(email);
        appUser.setCreationDate(LocalDateTime.now());
        appUser.setPhoneNumber(phonenumber);
        appUser.setSurname(Surname);
        appUser.setName(name);
        appUser.setGender(gender);
        appUser.setSpecialization(specialization);
        appUser.setCity(city);
        if (trener) {
            appUser.getUserRoles().add((UserRole) userRoleService.getUserRole());
            appUser.getUserRoles().add((UserRole) userRoleService.getUserRole2());

            ProtegeList protegeList = new ProtegeList();
            protegeList = protegeListRepository.save(protegeList);
            appUser.setPodopieczni(protegeList);
        } else {
            appUser.getUserRoles().add((UserRole) userRoleService.getUserRole());
        }

        try {
            appUserRepository.saveAndFlush(appUser);
            if(trener){
                ProtegeList protegeList = appUser.getPodopieczni();
                protegeList.setTrener(appUser);
                protegeListRepository.save(protegeList);
            }
        } catch (ConstraintViolationException cve) {
            return false;
        }
        return true;
    }


    public Optional<AppUser> getAppUserById(Long id) {
        return appUserRepository.findById(id);

    }


    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }


    public void remove(Long appUserid) {
        appUserRepository.deleteById(appUserid);
    }


    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }


    public void updateProfil(AppUserUpdateRequestDto dto) {
        //wyciaganie uzytkownika z bazy

        Optional<AppUser> user = appUserRepository.findById(dto.getIdUserEdited());
        if (user.isPresent()) {
            AppUser appUser = user.get();

            // sprawdzamy czy wypelnil formularz
            if (dto.getNameUserEdited() != null) {
                appUser.setName(dto.getNameUserEdited());
            }
            if (dto.getSurnameUserEdited() != null) {
                appUser.setSurname(dto.getSurnameUserEdited());
            }

            if (dto.getEmailUserEdited() != null) {
                appUser.setEmail(dto.getEmailUserEdited());
            }

            if (dto.getCityUserEdited() != null) {
                appUser.setCity(dto.getCityUserEdited());
            }
            if (dto.getPhonenumberUserEdited() != null) {
                appUser.setPhoneNumber(dto.getPhonenumberUserEdited());
            }
            // zapis do bazy
            appUserRepository.saveAndFlush(appUser);
        }
    }

    public List<AppUser> getAllProteges() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AppUser> appUser = findByUsername(user.getUsername());
        AppUser optionaAppuser = appUser.get();

        return optionaAppuser.getPodopieczni().getProtegeList();
    }


    public void addProtegeToList(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AppUser> appUser = findByUsername(user.getUsername());
        AppUser trenerUser = appUser.get();

        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        AppUser protege = appUserOptional.get();

        trenerUser.getPodopieczni().getProtegeList().add(protege);
        protege.setListaDoKtorejNaleze(trenerUser.getPodopieczni());

        protegeListRepository.save(trenerUser.getPodopieczni());
        appUserRepository.save(trenerUser);
        appUserRepository.save(protege);
    }


    public List<AppUser> searchUsername(String usernameSearched) {
        return appUserRepository.findAllByUsernameContaining(usernameSearched);
    }
}
