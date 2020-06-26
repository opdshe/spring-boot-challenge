package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRegisterRequestDto {
    private String name;

    private String address;

    private String email;

    private String phone;

    public User toEntity() {
        return User.builder()
                .name(name)
                .address(address)
                .email(email)
                .phone(phone)
                .build();
    }

    @Builder
    public UserRegisterRequestDto(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
