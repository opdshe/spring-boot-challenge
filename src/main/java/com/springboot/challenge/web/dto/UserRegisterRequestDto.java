package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.user.User;

import javax.persistence.Column;

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
}
