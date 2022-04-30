package com.cos.photogramstart.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class) // RuntimeException이 발생하는 모든 Exception을 가로챈다
    public String validationException(RuntimeException e) {
        return e.getMessage();
    }
}
