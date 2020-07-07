package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.domain.orderitem.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageResponseDto {
    private static final int DEFAULT_OFFSET = 1;
    private static final String AND = "외";
    private static final String COUNT_UNIT = "개";

    private final String orderName;

    private final int totalPrice;

    private final LocalDateTime orderDateTime;

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
        return orderItems.get(0).getItem().getName() + AND + (orderItems.size() - DEFAULT_OFFSET) + COUNT_UNIT;
    }
}
