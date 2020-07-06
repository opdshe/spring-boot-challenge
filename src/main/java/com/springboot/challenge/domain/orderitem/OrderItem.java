package com.springboot.challenge.domain.orderitem;

import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.order.Orders;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @Column(name = "order_price")
    private int orderPrice;

    private int count;

    @Builder
    public OrderItem(Orders order, Item item, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = item.getPrice()*count;
        this.count = count;
    }
}
