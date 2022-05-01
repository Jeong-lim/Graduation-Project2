package com.cos.photogramstart.handler.ex;

import java.util.Map;


//
public class CustomValidationApiException extends RuntimeException {

    // 객체를 구분할 때!!
    private static final long serialVersionUID = 1L;

    private  Map<String, String> errorMap;

    public CustomValidationApiException(String message) {
        super(message); // 부모한테 던져만 주면 된다.

    }

    public CustomValidationApiException(String message, Map<String, String> errorMap) {
        super(message); // 부모한테 던져만 주면 된다.
        this.errorMap = errorMap;

    }

    // getter 자동 생성 Alt + Insert
    public Map<String, String> getErrorMap() {
        return errorMap;
    }

}


