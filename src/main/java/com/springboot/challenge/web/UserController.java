package com.springboot.challenge.web;

import com.springboot.challenge.service.UserService;
import com.springboot.challenge.web.dto.LoginRequestDto;
import com.springboot.challenge.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/auth/login")
    public String loginProcess(@RequestBody LoginRequestDto requestDto, HttpSession session) {
        UserResponseDto userDto = userService.findByMemId(requestDto.getMemId());
        if (userDto == null) {
            System.out.println("해당 사용자 없음 ");
            return "/login";
        }
        if (!requestDto.getPassword().equals(userDto.getPassword())) {
            System.out.println("비밀번호 불일치 ");
            return "/login";
        }
        session.setAttribute("user", userDto);
        System.out.println("로그인 성공 ");
        return "redirect:/items";
    }
}
