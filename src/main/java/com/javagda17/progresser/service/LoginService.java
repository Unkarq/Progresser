package com.javagda17.progresser.service;

import com.javagda17.progresser.model.AppUser;
import com.javagda17.progresser.model.UserRole;
import com.javagda17.progresser.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;
    // to jest metoda domyślnie wywoływana przez spring security podczas logowania
    // po zalogowaniu (wpisaniu login i password) wywoła się ta metoda w celu odnalezienia użytkownika
    // z nazwą z parametru.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);
        if(appUserOptional.isPresent()){
            AppUser appUser = appUserOptional.get();
            // todo: uprawnienia
            Set<String> grantedAuthoritySet = new HashSet<>();
            //pobieramy rolke uzytkownika
            for (UserRole role : appUser.getUserRoles()){
                // zamieniamy role na SimpleGrantedAuthority
                grantedAuthoritySet.add(role.getName().replace("ROLE_", ""));
            }
            String[] authorities = grantedAuthoritySet.toArray(new String[grantedAuthoritySet.size()]);
            // za porównanie hasła odpowiada spring
            return User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .disabled(false)
                    .roles(authorities)
                    .build();
        }// nie wpisujemy tutaj return nu
        throw new UsernameNotFoundException("Username: " +username + " could not be found.");
    }

}