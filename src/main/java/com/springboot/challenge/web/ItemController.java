package com.springboot.challenge.web;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.service.ItemService;
import com.springboot.challenge.web.dto.ItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private static final Integer DEFAULT_PAGE = 1;
    private final ItemService itemService;

    @GetMapping("/products")
    public String products(@RequestParam(value = "category", required = false) Category category,
                        @RequestParam(value = "page", required = false) Integer page, Model model) {
        if(page == null){
            page = DEFAULT_PAGE;
        }
        PageRequest pageRequest = PageRequest.of(page - DEFAULT_PAGE, 20, Sort.by(Sort.Direction.DESC, "sales"));
        Page<ItemResponseDto> findPage = itemService.findAll(category, pageRequest);
        model.addAttribute("items", findPage.getContent());
        model.addAttribute("pages", IntStream.rangeClosed(1, findPage.getTotalPages())
                .boxed()
                .collect(Collectors.toList()));
        return "products";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "item-num") Long id, Model model) {
        ItemResponseDto responseDto = itemService.findById(id);
        model.addAttribute("item", responseDto);
        return "detail";
    }

    @GetMapping("/bag")
    public String bag(HttpSession httpSession, Model model){
        Map<Long, Integer > bag = (Map<Long, Integer>) httpSession.getAttribute("bag");
        List<Long> itemIds = new ArrayList<>(bag.keySet());
        List<ItemResponseDto> items = itemService.findByIdIn(itemIds);
        for (ItemResponseDto item : items) {
            item.setCount(bag.get(item.getId()));
            item.setTotalPrice(item.getPrice()*item.getCount());
        }
        model.addAttribute("items", items);
        return "bag";
    }
}
