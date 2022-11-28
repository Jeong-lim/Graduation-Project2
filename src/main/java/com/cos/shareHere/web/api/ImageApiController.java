package com.cos.shareHere.web.api;

import com.cos.shareHere.config.auth.PrincipalDetails;
import com.cos.shareHere.domain.image.Image;
import com.cos.shareHere.service.ImageService;
import com.cos.shareHere.service.LikesService;
import com.cos.shareHere.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ImageApiController {
    
    private final ImageService imageService;
    private final LikesService likesService;

    @GetMapping("/api/image/all")
    public ResponseEntity<?> imageStoryAll(
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Image> images = imageService.모든이미지(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(
                new CMRespDto<>(1, "성공", images), HttpStatus.OK);
    }

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStory(
        @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Image> images = imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(
                new CMRespDto<>(1, "성공", images), HttpStatus.OK);
    }
    
    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable Integer imageId, 
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
                likesService.좋아요(imageId, principalDetails.getUser().getId());
                return new ResponseEntity<>(
            new CMRespDto<>(1, "좋아요 성공", null), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable Integer imageId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
                likesService.좋아요취소(imageId, principalDetails.getUser().getId());
                return new ResponseEntity<>(
            new CMRespDto<>(1, "좋아요취소 성공", null), HttpStatus.OK);
    }
    
}
