package com.springboot.challenge.web.dto;

import com.springboot.challenge.domain.order.Orders;
import com.springboot.challenge.domain.orderitem.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDtoForMyPage {
    private static final int DEFAULT_COUNT = 1;
    private static final String AND = " 외 ";
    private static final String COUNT_UNIT = "개";

    private final String orderName;

    private final int totalPrice;

    private final LocalDateTime orderDateTime;

    public OrderDtoForMyPage(Orders orders) {
        this.orderName = setOrderName(orders.getOrderItems());
        this.totalPrice = orders.getOrderItems().stream()
                .map(OrderItem::getOrderPrice)
                .reduce(Integer::sum)
                .orElseThrow(()->new IllegalArgumentException("해당 주문의 금액이 존재하지 않습니다. "));
        this.orderDateTime = orders.getOrderDatetime();
    }

    private String setOrderName(List<OrderItem> orderItems) {
        String firstItemName = orderItems.stream()
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("해당 주문에 상품이 존재하지 않습니다. "))
                .getItem()
                .getName();
        if (orderItems.size() == DEFAULT_COUNT) {
            return firstItemName;
        }
        return firstItemName + AND + (orderItems.size() - DEFAULT_COUNT) + COUNT_UNIT;
    }
}
