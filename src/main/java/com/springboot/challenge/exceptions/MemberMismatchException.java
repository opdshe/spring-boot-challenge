package com.springboot.challenge.exceptions;

public class MemberMismatchException extends RuntimeException{
    private static final String MEMBER_MISMATCH_MESSAGE = "해당 사용자가 존재하지 않습니다. id = ";
    public MemberMismatchException(String message) {
        super(MEMBER_MISMATCH_MESSAGE + message);
    }
}
