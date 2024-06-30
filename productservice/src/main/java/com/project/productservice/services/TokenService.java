package com.project.productservice.services;

import com.project.productservice.dtos.UserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    private RestTemplate restTemplate = new RestTemplate();
    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean validateToken(String token) {
        UserDto userDto= restTemplate.postForObject("http://localhost:9000/users/validate/"+token, null, UserDto.class);
        return userDto != null;

    }
}
