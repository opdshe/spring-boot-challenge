package com.springboot.challenge.web;

import com.springboot.challenge.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

    @GetMapping("/api/v1/insert")
    public String insert (@RequestParam(value = "item-num") Long id,
                          @RequestParam(value = "count") Integer count,
                          HttpSession httpSession){
        Object bagAttribute = httpSession.getAttribute("bag");
        Map<Long, Integer> bag = (Map<Long, Integer>) bagAttribute;
        bag.put(id, bag.getOrDefault(id, 0)+count);
        httpSession.setAttribute("bag", bag);
        return bag.get(id).toString();
    }
}
