package com.cos.photogramstart.handler.ex;

public class CustomException extends RuntimeException {

    // 객체를 구분할 때!!
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message); // 부모한테 던져만 주면 된다.

    }
}


