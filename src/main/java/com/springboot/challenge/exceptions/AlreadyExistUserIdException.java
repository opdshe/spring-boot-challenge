package com.springboot.challenge.exceptions;

import org.springframework.web.util.NestedServletException;

public class AlreadyExistUserIdException extends NestedServletException {
    private static final String ALREADY_EXIST_USER_ID_MESSAGE = "이미 등록되어 있는 아이디입니다. ";
    public AlreadyExistUserIdException(String userId) {
        super(ALREADY_EXIST_USER_ID_MESSAGE + userId);
    }
}
