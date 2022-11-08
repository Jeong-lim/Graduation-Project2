package com.cos.shareHere.handler;

import com.cos.shareHere.handler.ex.CustomApiException;
import com.cos.shareHere.handler.ex.CustomException;
import com.cos.shareHere.handler.ex.CustomValidationApiException;
import com.cos.shareHere.handler.ex.CustomValidationException;
import com.cos.shareHere.util.Script;
import com.cos.shareHere.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice // 모든 Exception을 낚아채는
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomException.class) // RuntimeException이 발동하느 모든 Exception을 가로챔
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(CustomValidationException.class) // RuntimeException이 발동하느 모든 Exception을 가로챔
    public String validationException(CustomValidationException e) {
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script가 좋음 - 클라이언트가 응답받음
        // 2. Ajax 통신 - CMRespDao 코드로 개발자가 받는 것
        // 3. Android 통신 - CMRespDto 코드로 개발자가 받음
        if(e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }


}
