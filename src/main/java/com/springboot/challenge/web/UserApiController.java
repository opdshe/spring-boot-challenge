package com.springboot.challenge.web;

import com.springboot.challenge.exceptions.AlreadyExistUserIdException;
import com.springboot.challenge.service.UserService;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/v1/register")
    public Long register (@RequestBody MemberRegisterRequestDto memberRegisterRequestDto) throws AlreadyExistUserIdException {
        return userService.register(memberRegisterRequestDto);
    }
}
