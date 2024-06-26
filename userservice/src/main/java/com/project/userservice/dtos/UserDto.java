package com.project.userservice.dtos;

import com.project.userservice.models.Role;
import com.project.userservice.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
