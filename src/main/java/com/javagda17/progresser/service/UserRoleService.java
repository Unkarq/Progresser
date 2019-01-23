package com.javagda17.progresser.service;

import com.javagda17.progresser.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    public Object getUserRole() {
        Optional<Object> optionalUserRole = userRoleRepository.findByName("ROLE_USER");

        if (optionalUserRole.isPresent()) {
            return optionalUserRole.get();
        }
        throw new DataIntegrityViolationException("USER_ROLE should exist in database.");
    }

    public Object getUserRole2() {
        Optional<Object> optionalUserRole = userRoleRepository.findByName("ROLE_TRENER");

        if (optionalUserRole.isPresent()) {
            return optionalUserRole.get();
        }
        throw new DataIntegrityViolationException("TRENER_ROLE should exist in database.");
    }

}

