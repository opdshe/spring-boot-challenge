package com.springboot.challenge.exceptions;

public class OverStockException  extends RuntimeException{
    private static final String message = "죄송합니다. 고객님이 선택하신 상품 개수가 재고보다 많습니다.";
    public OverStockException() {
        super(message);
    }
}
