package com.springboot.challenge.web;

import com.springboot.challenge.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterApiController {
    private UserRepository userRepository;

    @PostMapping("/api/v1/")
}
