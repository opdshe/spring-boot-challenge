package com.springboot.challenge.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    private int price;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String thumbnailUrl;

    @Builder
    public Item(String name, int price, int stockQuantity, Category category, String thumbnailUrl) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.thumbnailUrl = thumbnailUrl;
    }
}