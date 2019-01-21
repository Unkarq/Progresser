package com.javagda17.progresser.service;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean tryRegister(String username, String password) {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));

        try {


            appUserRepository.saveAndFlush(appUser);
        }catch (ConstraintViolationException cve){
            return false;
        }
        return true;
    }
}