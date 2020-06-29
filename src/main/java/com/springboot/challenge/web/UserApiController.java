package com.springboot.challenge.web;

import com.springboot.challenge.service.UserService;
import com.springboot.challenge.web.dto.UserRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/user")
    public Long register (@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return userService.register(userRegisterRequestDto);
    }
}
