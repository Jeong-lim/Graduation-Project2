package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static com.cos.photogramstart.util.Script.back;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // RuntimeException이 발생하는 모든 Exception을 가로챈다
    public String validationException(CustomValidationException e) { // 제네릭 타입을 리턴할 때는 ?를 쓰는게 가장 편함
        // CMRespDto.Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 좋음
        // 2. Ajax 통신 - CMRespDto
        // 3. Android 통신 = CMRespDtp
        return back(e.getErrorMap().toString());

//        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap()); // 실패하면
    }
}
