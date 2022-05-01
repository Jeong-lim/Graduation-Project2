package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static com.cos.photogramstart.util.Script.back;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    // 자바스크립트 리턴
    @ExceptionHandler(CustomValidationException.class) // RuntimeException이 발생하는 모든 Exception을 가로챈다
    public String validationException(CustomValidationException e) { // 제네릭 타입을 리턴할 때는 ?를 쓰는게 가장 편함
        // CMRespDto.Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 좋음
        // 2. Ajax 통신 - CMRespDto
        // 3. Android 통신 = CMRespDto
        return back(e.getErrorMap().toString());

//        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap()); // 실패하면
    }

    // 데이터 리턴 (AJAX 응답)
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);

    }
}
