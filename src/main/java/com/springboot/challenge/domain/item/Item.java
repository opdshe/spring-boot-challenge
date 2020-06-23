package com.springboot.challenge.domain.item;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    private int price;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String thumbnail;

    private int sales;

    @Builder
    public Item(String name, int price, int stockQuantity, Category category, String thumbnailUrl, int sales) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.thumbnail = thumbnailUrl;
        this.sales = sales;
    }

    public void sell() {
        this.stockQuantity--;
    }
}
