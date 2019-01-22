package com.javagda17.progresser.configuration;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.UserRole;
import com.javagda17.progresser.repository.AppUserRepository;
import com.javagda17.progresser.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class DataInitializer implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        checkAndLoadRoles();
        checkAndLoadUsers();
    }

    private void checkAndLoadUsers() {
        if (!checkUser("admin")) {
            createUser("admin", "admin", "ROLE_USER", "ROLE_ADMIN");
        }

        if (!checkUser("user")) {
            createUser("user", "user", "ROLE_USER");
        }

        if (!checkUser("trener")) {
            createUser("trener", "trener", "ROLE_TRENER");

        }
    }

    private void createUser(String username, String password, String... roles) {


        Set<UserRole> userRoles = new HashSet<>();
        for (String role : roles) {
            userRoles.add(findRole(role));

        }

        AppUser appUser = new AppUser();
        appUser.setUserRoles(userRoles);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setUsername(username);


        appUserRepository.saveAndFlush(appUser);
    }

    private UserRole findRole(String role) {
        UserRole userRole = userRoleRepository.getByName(role);
        return userRole;
    }

    private boolean checkUser(String username) {
        return appUserRepository.findByUsername(username).isPresent();
    }

    private void checkAndLoadRoles() {
        if (!checkRole("ROLE_USER")) {
            createRole("ROLE_USER");
        }
        if (!checkRole("ROLE_ADMIN")) {
            createRole("ROLE_ADMIN");
        }
        if (!checkRole("ROLE_TRENER")) {
            createRole("ROLE_TRENER");
        }
    }


    private void createRole(String role) {
        UserRole createdRole = new UserRole(null, role);
        userRoleRepository.saveAndFlush(createdRole);
    }

    private boolean checkRole(String role) {
        return userRoleRepository.findByName(role).isPresent();
    }


}