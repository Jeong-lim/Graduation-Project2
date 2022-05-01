package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
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
public class UseApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto, // 유효성 검사
            BindingResult bindingResult, // 꼭 @Valid 가 적혀있는 다음 파라메터 적어야됨
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(bindingResult.hasErrors())

    {
        Map<String, String> errorMap = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) { // Error를 다 모아줌
            errorMap.put(error.getField(), error.getDefaultMessage());
            System.out.println(error.getDefaultMessage());
        }
        throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
    } else {
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity); // 세션 정보 변경
            return new CMRespDto<>(1, "회원수정완료", userEntity);
            // 응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
        }

    }// 요청이 들어오면 수정을 하고 수정된 결과를 받아서 ajax로 호출한 곳으로 응답만 해줌
}
