package com.javagda17.progresser.service;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.Gender;
import com.javagda17.progresser.model.Specialization;
import com.javagda17.progresser.model.UserRole;
import com.javagda17.progresser.repository.AppUserRepository;
import com.javagda17.progresser.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {
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
        if(trener==true){
            appUser.getUserRoles().add((UserRole) userRoleService.getUserRole());
            appUser.getUserRoles().add((UserRole) userRoleService.getUserRole2());
        }else {

            appUser.getUserRoles().add((UserRole) userRoleService.getUserRole());

        }
        try {


            appUserRepository.saveAndFlush(appUser);
        }catch (ConstraintViolationException cve){
            return false;
        }
        return true;
    }

    public boolean updateAppUser(AppUser appUser) {
        Optional<AppUser> appUserOptional = getAppUserById(appUser.getId());
        if (appUserOptional.isPresent()) {
            AppUser appUserFromDB = appUserOptional.get();

            appUserFromDB.setName(appUser.getName());
            appUserFromDB.setSurname(appUser.getSurname());
            appUserFromDB.setEmail(appUser.getEmail());
            appUserFromDB.setUserRoles(appUser.getUserRoles());
            appUserFromDB.setCity(appUser.getCity());
            appUserFromDB.setPhoneNumber(appUser.getPhoneNumber());
            appUserFromDB.setGender(appUser.getGender());
            appUserFromDB.setSpecialization(appUser.getSpecialization());


            appUserRepository.saveAndFlush(appUserFromDB);
            return true;
        }
        return false;
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


    public Object getAllInfo() {

        return null;
    }

    public void updateAppUser(String appuserName, String appUserSurname, Gender gender, String email, Specialization specialization, String appUserCity, String appUserPhone) {
    }

    public AppUser getCurrentUser() {
        Optional<AppUser> currentUser = appUserRepository.findByUsername(((User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return currentUser.orElse(null);
    }

}
