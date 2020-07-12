package com.springboot.challenge.exceptions;

public class DoNotHaveUserSessionAttributeException extends RuntimeException{
    private static final String message = "로그인 정보가 없습니다. 로그인 후 이용해 주세요. ";
    public DoNotHaveUserSessionAttributeException() {
        super(message);
    }
}
