package com.project.userservice.security.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.userservice.models.User;
import com.project.userservice.repository.UserRepo;
import com.project.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@JsonDeserialize
public class CustomUserDetailsService implements UserDetailsService {

    UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByEmail(username);
        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return new CustomUserDetails(optionalUser.get());
    }

}
