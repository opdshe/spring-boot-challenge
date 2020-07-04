package com.springboot.challenge.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BagController {
    @GetMapping("/bag")
    public String bag(){
        return "bag";
    }
}
