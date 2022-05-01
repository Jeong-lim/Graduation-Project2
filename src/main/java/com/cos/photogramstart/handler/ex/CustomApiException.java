package com.cos.photogramstart.handler.ex;

// 터졌을 때 처리하는 부분
public class CustomApiException extends RuntimeException {
    // 객체를 구분할 때!!
    private static final long serialVersionUID = 1L;
    public CustomApiException(String message) {
        super(message); // 부모한테 던져만 주면 된다.

    }
}



