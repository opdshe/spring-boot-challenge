package com.springboot.challenge.web;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final ItemRepository itemRepository;

    @GetMapping("/")
    public String showAll(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "sales"));
        Page<Item> findPage = itemRepository.findAll(pageRequest);
        model.addAttribute("items", findPage.getContent());
        model.addAttribute("pages",IntStream.rangeClosed(1, findPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList()));
        System.out.println("called showAll method");
        return "index";
    }

    @GetMapping("/items")
    public String filterByCategory(@RequestParam (value = "category",required = false) Category category,
                                   @RequestParam (value="page", required = false) int page, Model model) {
        PageRequest pageRequest = PageRequest.of(page-1, 20, Sort.by(Sort.Direction.DESC, "sales"));
        Page<Item> findPage = itemRepository.findAllByCategory(category, pageRequest);
        model.addAttribute("items", findPage.getContent());
        model.addAttribute("pages", IntStream.rangeClosed(1, findPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList()));
        System.out.println("called filterByCategory");
        return "index";
    }

}
