package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.domain.orderitem.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageResponseDto {
    private String orderName;

    private int totalPrice;

    private LocalDateTime orderDateTime;

    public MyPageResponseDto(Orders orders) {
        this.orderName = setOrderName(orders.getOrderItems());
        this.totalPrice = orders.getOrderItems().stream()
                .map(OrderItem::getOrderPrice)
                .reduce(Integer::sum)
                .get();
        this.orderDateTime = orders.getOrderDatetime();
    }

    private String setOrderName(List<OrderItem> orderItems) {
        if (orderItems.size() == 1) {
            return orderItems.get(0).getItem().getName();
        }
        return orderItems.get(0).getItem().getName() + "외" + (orderItems.size() - 1) + "개";
    }
}
