package com.springboot.challenge.exceptions;

public class EmptyBagException extends RuntimeException{
    private static final String message = "장바구니가 비어있습니다. ";

    public EmptyBagException() {
        super(message);
    }
}
