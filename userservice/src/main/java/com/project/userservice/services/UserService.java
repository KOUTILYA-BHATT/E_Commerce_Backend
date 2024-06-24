package com.project.userservice.services;

import com.project.userservice.models.Token;
import com.project.userservice.models.User;
import com.project.userservice.repository.TokenRepo;
import com.project.userservice.repository.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserRepo userRepo;
    private TokenRepo tokenRepo;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepo, TokenRepo tokenRepo) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
    }

    public User signUp(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }
    public Token login(String email, String password) {
        //Verify if the user exists
        Optional<User> user=userRepo.findByEmail(email);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found"+ "Email");
        }
        //Verify that the user password is correct
        if(!bCryptPasswordEncoder.matches(password,user.get().getHashedPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        Token token = generateToken(user.get());
        return tokenRepo.save(token);

    }

    public User validateToken(String tokenValue) {
        Optional<Token> token= tokenRepo.findByValueAndExpiryAtGreaterThan(tokenValue,new Date());
        if(token.isEmpty()) {
            throw new BadCredentialsException("Invalid Credentials");
        }
        return token.get().getUser();
    }

    private Token generateToken(User user) {
        Token token = new Token();
        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        LocalDate currentDate = LocalDate.now();
        LocalDate thirtyDaysLater = currentDate.plusDays(30);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(expiryDate);
        return token;
    }
}
