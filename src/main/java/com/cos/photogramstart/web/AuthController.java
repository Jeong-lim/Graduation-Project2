package com.cos.photogramstart.web;


import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // final 필드를 DI 할 때 사용
@Controller // 1. IOC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

//    public AuthController(AuthService authService) {
//        this.authService = authService; // 의존성 주입
//    }


    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    // 회원가입 버튼 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key = value(x-www-form-urlencoded) 형식으로 날라온다.
//        log.info(String.valueOf(signupDto)); // 잘 담아왔으면 디비에 INSERT를 해줘야됨

        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error:bindingResult.getFieldErrors()) { // Error를 다 모아줌
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }
            throw new RuntimeException("유효성검사 실패함");
        } else {
            // User <- SignupDto
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            return "auth/signin"; // 회원가입이 성공하면
        }

//        log.info(user.toString());

    } // 회원 가입 진행
    // CSRF 토큰 -> 서버가 응답할 때 시큐리티가 CSRF 토큰을 심게 된다.(난수)
    // 그리고 다시 되돌려받았을 때 CSRF 토큰이 있는지 다시 검사를 하게 된다.
    // 그래서 토큰을 일단 비활성화
}

//인증을 위한 컨트롤러