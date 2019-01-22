package com.javagda17.progresser.repository;

import com.javagda17.progresser.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    UserRole getByName(String role_user);

    Optional<Object> findByName(String role);

}
