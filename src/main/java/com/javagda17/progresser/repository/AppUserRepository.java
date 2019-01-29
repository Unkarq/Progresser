package com.javagda17.progresser.repository;

import com.javagda17.progresser.model.AppUser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {


   Optional<AppUser> findByUsername(String username);
   List<AppUser> findAllByUsernameContaining(String username);
}
