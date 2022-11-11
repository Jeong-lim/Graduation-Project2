package com.cos.shareHere.web.api;

import com.cos.shareHere.config.auth.PrincipalDetails;
import com.cos.shareHere.domain.user.User;
import com.cos.shareHere.handler.ex.CustomValidationApiException;
import com.cos.shareHere.service.UserService;
import com.cos.shareHere.web.dto.CMRespDto;
import com.cos.shareHere.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult, // 꼭 @Valid가 적혀있는 것 바로 뒤에 적어줘야됌
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw  new CustomValidationApiException("유효성 검사 실패함", errorMap);
        } else {
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity); // 세션 정보 변경
            return new CMRespDto<>(1, "회원수정 완료", userEntity); // 응답 시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
        }
    }
}