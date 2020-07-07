package com.springboot.challenge.web;

import com.springboot.challenge.service.MemberService;
import com.springboot.challenge.web.dto.MemberRegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/register")
    public Long register (@RequestBody MemberRegisterRequestDto memberRegisterRequestDto) {
        return memberService.register(memberRegisterRequestDto);
    }
}
