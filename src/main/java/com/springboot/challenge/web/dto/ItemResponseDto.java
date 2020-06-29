package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.item.Category;
import lombok.Builder;

public class ItemResponseDto {
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private Category category;

    private String thumbnail;

    private int sales;

    @Builder
    public ItemResponseDto(Long id, String name, int price, int stockQuantity, Category category, String thumbnail, int sales) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.thumbnail = thumbnail;
        this.sales = sales;
    }
}
