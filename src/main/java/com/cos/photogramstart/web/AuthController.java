package com.cos.photogramstart.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 1. IOC 2. 파일을 리턴하는 컨트롤러
public class AuthController { // 인증을 위한 컨트롤러

    @GetMapping("/auth/signin")
    public String signinForm()  {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }
    // 회원가입버튼 -> /auth/signup -> /auth/signin
    // 회원가입 버튼 X
    @PostMapping("/auth/signup")
    public String signup()  { // 회원가입 진행
        System.out.println("signup 실행됨");
        return "auth/signin";
    }
}
