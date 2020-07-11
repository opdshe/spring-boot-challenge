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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
public class ItemController {
    private static final String REQUEST_PARAMETER_CATEGORY = "category";
    private static final String REQUEST_PARAMETER_PAGE = "page";
    private static final String REQUEST_PARAMETER_ITEM_NUM = "item-num";
    private static final String MODEL_ATTRIBUTE_ITEMS = "items";
    private static final String MODEL_ATTRIBUTE_DETAIL_ITEM = "detailItem";
    private static final String MODEL_ATTRIBUTE_PAGES = "pages";
    private static final String SORTING_CRITERION = "sales";
    private static final int DEFAULT_PAGE = 1;
    private static final int NUMBER_OF_ITEMS_ON_ONE_PAGE = 20;


    private final ItemService itemService;

    @GetMapping("/products")
    public String products(@RequestParam(value = REQUEST_PARAMETER_CATEGORY, required = false) Category category,
                           @RequestParam(value = REQUEST_PARAMETER_PAGE, required = false) Integer page, Model model) {
        if (page == null) {
            page = DEFAULT_PAGE;
        }
        PageRequest pageRequest = PageRequest.of(page - DEFAULT_PAGE, NUMBER_OF_ITEMS_ON_ONE_PAGE,
                Sort.by(Sort.Direction.DESC, SORTING_CRITERION));
        Page<ItemResponseDto> pageFindByCategory = itemService.getPageFindByCategory(category, pageRequest);
        model.addAttribute(MODEL_ATTRIBUTE_ITEMS, pageFindByCategory.getContent());
        model.addAttribute(MODEL_ATTRIBUTE_PAGES, IntStream.rangeClosed(DEFAULT_PAGE, pageFindByCategory.getTotalPages())
                .boxed()
                .collect(Collectors.toList()));
        return "products";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = REQUEST_PARAMETER_ITEM_NUM) Long id, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_DETAIL_ITEM, itemService.getITemResponseDtoFindById(id));
        return "detail";
    }

    @GetMapping("/bag")
    public String bag(HttpSession httpSession, Model model) {
        model.addAttribute(MODEL_ATTRIBUTE_ITEMS, itemService.getBagList(httpSession));
        return "bag";
    }
}
