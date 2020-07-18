package com.springboot.challenge.web;

import com.springboot.challenge.service.TransactionService;
import com.springboot.challenge.web.dto.DetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TransactionApiController {
    private final TransactionService transactionService;

    @PostMapping("/api/v1/bag/insert")
    public Long insert(@RequestBody DetailResponseDto responseDto, HttpSession httpSession) {
        return transactionService.insert(responseDto, httpSession);
    }

    @PostMapping("/api/v1/bag/delete")
    public void delete(@RequestParam(value = "removalObjects[]") List<Long> removalObjects, HttpSession httpSession) {
        transactionService.delete(removalObjects, httpSession);
    }

    @PostMapping("/api/v1/buy")
    public Long buy(HttpSession httpSession) {
        return transactionService.buy(httpSession);
    }
}
