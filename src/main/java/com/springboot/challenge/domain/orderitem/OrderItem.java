package com.springboot.challenge.domain.orderitem;

import com.springboot.challenge.domain.item.Item;
import com.springboot.challenge.domain.order.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id @GeneratedValue
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

    public void setOrderItemInfo(Orders orders, Item item) {

    }
}
