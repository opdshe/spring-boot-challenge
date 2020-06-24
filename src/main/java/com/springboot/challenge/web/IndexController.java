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

    @GetMapping("/items")
    public String items(@RequestParam (value = "category",required = false) Category category,
                                   @RequestParam (value="page", required = false) Integer page, Model model) {
        PageRequest pageRequest = PageRequest.of(page-1, 20, Sort.by(Sort.Direction.DESC, "sales"));
        Page<Item> findPage = getPage(category, pageRequest);
        model.addAttribute("items", findPage.getContent());
        model.addAttribute("pages", IntStream.rangeClosed(1, findPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList()));
        return "index";
    }

    private Page<Item> getPage(Category category, PageRequest pageRequest) {
        if(category==null){
            return itemRepository.findAll(pageRequest);
        }
        return itemRepository.findAllByCategory(category, pageRequest);
    }
}
