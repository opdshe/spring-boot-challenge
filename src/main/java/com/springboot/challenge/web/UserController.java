package com.springboot.challenge.web;

import com.springboot.challenge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        return "login";
    }
}
