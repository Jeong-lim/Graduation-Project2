package com.cos.shareHere.web;

import com.cos.shareHere.config.auth.PrincipalDetails;
import com.cos.shareHere.service.UserService;
import com.cos.shareHere.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    
    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable Integer pageUserId, Model model,
    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Integer id,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // System.out.println("세션 정보 확인 : " + principalDetails.getUser());
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        // System.out.println("세션 정보 확인 2 : " + mPrincipalDetails.getUser());
        return "user/update";
    }
}
