package com.springboot.challenge.web;

import com.springboot.challenge.service.TransactionService;
import com.springboot.challenge.web.dto.DetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class TransactionApiController {
    private final TransactionService transactionService;

    @PostMapping("/api/v1/auth/insert")
    public Long insert(@RequestBody DetailResponseDto responseDto, HttpSession httpSession) {
        return transactionService.insert(responseDto, httpSession);
    }

    @PostMapping("/api/v1/auth/buy")
    public String buy(HttpSession httpSession) {
        return transactionService.buy(httpSession);
    }
}
