package com.springboot.challenge.web;

import com.springboot.challenge.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemApiController {
    private final ItemService itemService;

}
