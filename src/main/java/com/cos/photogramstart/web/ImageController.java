package com.cos.photogramstart.web;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.ImageSerivice;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageSerivice imageSerivice;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story"; // 로그인 끝
    }

    @GetMapping({"/image/popular"})
    public String popular() {
        return "image/popular"; // 로그인 끝
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto,
                              @AuthenticationPrincipal PrincipalDetails principalDetails) {
        // 서비스 호출
        imageSerivice.사진업로드(imageUploadDto, principalDetails); // 실제 로직은 서비스에서 구현
        return "redirect:/user/" + principalDetails.getUser().getId();
    }



}
