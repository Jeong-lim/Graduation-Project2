package com.cos.photogramstart.web;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id) {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update") // user의 어떤 번호를 업데이트 할건지
    public String update(@PathVariable int id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails// Authentication 객체에 바로 접근 가능
                         ) {
        // 1. 추천
        System.out.println("세션 정보 : "+principalDetails.getUser());

        // 2. 비추
//       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
//        System.out.println("직접찾은 세션 정보 : " + mPrincipalDetails.getUser());
//        return "user/update";

        return "user/update"; //principal은 접근 주체 또는 인증 주체

    }
}
