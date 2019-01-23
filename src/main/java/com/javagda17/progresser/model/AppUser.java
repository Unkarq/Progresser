package com.javagda17.progresser.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 4)
    @Column(unique = true)
    private String username;
    private String password;

    private String name;
    private String surname;
    private String gander;

    private String city;
    private String specialization;
    private LocalDateTime creationDate;
    @Email
    private String email;

    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

//    @OneToMany
//    private Set<AppUser> proteges;
//    //Todo:Konto Uzytkownik
    //login
    //haslo
    //płeć
    //imie
    //doswiadczenie
    //nazwisko
    //miasto
    //data zalozenia konta
    //plan treningowy
    //rodzaj konta (uzytkownik/trener/admin)
    //zdjecie/awatar - nice to have
    //waga
    //wzrost
    //zalecana dieta



}