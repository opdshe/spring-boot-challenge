package com.springboot.challenge.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
