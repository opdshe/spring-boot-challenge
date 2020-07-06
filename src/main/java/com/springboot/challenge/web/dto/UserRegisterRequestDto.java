package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@Getter
public class UserRegisterRequestDto {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String userId;

    private String password;

    private String name;

    private String address;

    private String email;

    private String phone;

    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .name(name)
                .password(passwordEncoder.encode(password))
                .address(address)
                .email(email)
                .phone(phone)
                .build();
    }

    @Builder
    public UserRegisterRequestDto(String userId, String password, String name, String address, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
