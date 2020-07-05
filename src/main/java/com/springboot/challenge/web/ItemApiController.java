package com.springboot.challenge.web;

import com.springboot.challenge.service.ItemService;
import com.springboot.challenge.web.dto.DetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private static final int DEFAULT_COUNT = 0;

    private final ItemService itemService;

    @PostMapping("/api/v1/auth/insert")
    public String insert(@RequestBody DetailResponseDto responseDto,
                         HttpSession httpSession) {
        Object bagAttribute = httpSession.getAttribute("bag");
        Map<Long, Integer> bag = (Map<Long, Integer>) bagAttribute;
        bag.put(responseDto.getId(), bag.getOrDefault(responseDto.getId(), DEFAULT_COUNT) + responseDto.getCount());
        httpSession.setAttribute("bag", bag);
        return bag.get(responseDto.getId()).toString();
    }
}
