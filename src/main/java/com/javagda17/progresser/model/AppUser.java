package com.javagda17.progresser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 4)
    @Column(unique = true)
    private String username;
    @Size(min = 4)
    private String password;

    private String name;
    private String surname;

    @Email
    private String email;

    private String phoneNumber;

}