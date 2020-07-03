package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.user.Member;
import com.springboot.challenge.domain.user.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRegisterRequestDto {
    private String memId;

    private String password;

    private String name;

    private String address;

    private String email;

    private String phone;

    private Role userRole;

    public Member toEntity() {
        return Member.builder()
                .memId(memId)
                .name(name)
                .password(password)
                .address(address)
                .email(email)
                .phone(phone)
                .build();
    }

    @Builder
    public UserRegisterRequestDto(String memId, String password, String name, String address, String email, String phone) {
        this.memId = memId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
