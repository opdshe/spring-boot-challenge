package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String memId;

    private String password;

    private String name;

    private String address;

    private String email;

    private String phone;

    private Role role;

    @Builder
    public UserResponseDto(String memId, String password, String name, String address, String email, String phone, Role role) {
        this.memId = memId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public Boolean isAdmin(){
        if (role.equals(Role.ADMIN)){
            return true;
        }
        return false;
    }
}
