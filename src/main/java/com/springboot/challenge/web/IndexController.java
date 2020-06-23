package com.springboot.challenge.web;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("items", itemRepository.findAll());
        return "index";
    }

    @GetMapping("/{category}")
    public String showOnlyOuter(@PathVariable Category category, Model model){
        model.addAttribute("items",itemRepository.findAllByCategory(category));
        return "index";
    }

}
