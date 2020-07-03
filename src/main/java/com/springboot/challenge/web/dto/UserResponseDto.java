package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.user.Role;
import com.springboot.challenge.domain.user.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;

    private String userId;

    private String password;

    private String name;

    private String address;

    private String email;

    private String phone;

    private Role role;

    public UserResponseDto(Member entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.role = entity.getRole();
    }
}
