package com.cos.photogramstart.service;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageSerivice {

    private final ImageRepository imageRepository;

    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        String imageFileName = imageUploadDto.getFile().getOriginalFilename(); // 실제 파일 이름이 들어감
    }
}
