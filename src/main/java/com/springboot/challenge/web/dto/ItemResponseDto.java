package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.item.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemResponseDto {
    private String name;

    private int price;

    private int stockQuantity;

    private Category category;

    private String thumbnail;

    private int sales;
}
