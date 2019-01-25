package com.javagda17.progresser.service;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;



}








