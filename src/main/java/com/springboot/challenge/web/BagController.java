package com.springboot.challenge.web;

import com.springboot.challenge.service.ItemService;
import com.springboot.challenge.web.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BagController {
    private final ItemService itemService;

    @GetMapping("/bag")
    public String bag(HttpSession httpSession, Model model){
        Map<Long, Integer > bag = (Map<Long, Integer>) httpSession.getAttribute("bag");
        List<Long> itemIds = new ArrayList<>(bag.keySet());
        List<ItemResponseDto> items = itemService.findByIdIn(itemIds);
        model.addAttribute("items", items);
        return "bag";
    }
}
