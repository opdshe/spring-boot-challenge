package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.item.Category;
import com.springboot.challenge.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDto {
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    private Category category;

    private String thumbnail;

    private int sales;

    private int count;

    private int totalPrice;

    public ItemResponseDto(Item entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
        this.category = entity.getCategory();
        this.thumbnail = entity.getThumbnail();
        this.sales = entity.getSales();
    }
}
