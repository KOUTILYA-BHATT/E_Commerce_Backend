package com.project.userservice.controllers;

import com.project.userservice.dtos.LogInRequestDto;
import com.project.userservice.dtos.SignUpRequestDto;
import com.project.userservice.dtos.UserDto;
import com.project.userservice.models.Token;
import com.project.userservice.models.User;
import com.project.userservice.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignUpRequestDto requestDto){
        User user = userService.signUp(requestDto.getEmail(),requestDto.getName(),requestDto.getPassword());
        return UserDto.from(user);
    }

    @GetMapping("/{id}")
    public UserDto getuser(@PathVariable("id") Long id){
        return new UserDto("koutilya","koutilya.bhatt@gmail.com");
    }

    @PostMapping("/login")
    public Token login(@RequestBody LogInRequestDto requestDto){
        Token token = userService.login(requestDto.getEmail(),requestDto.getPassword());
        return token;
    }

    @PostMapping("/validate/{token}")
    public UserDto validate(@PathVariable String token){
        try {
            return UserDto.from(userService.validateToken(token));
        }catch (Exception e){
            return null;
        }
    }
}
