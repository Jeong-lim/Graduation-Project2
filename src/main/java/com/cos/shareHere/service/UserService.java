package com.cos.shareHere.service;

import com.cos.shareHere.domain.subscribe.SubscibeRepository;
import com.cos.shareHere.domain.user.User;
import com.cos.shareHere.domain.user.UserRepository;
import com.cos.shareHere.handler.ex.CustomException;
import com.cos.shareHere.handler.ex.CustomValidationApiException;
import com.cos.shareHere.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SubscibeRepository subscibeRepository;

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int principalId) {
        UserProfileDto dto = new UserProfileDto();

        // SELECT * FROM IMAGE WHERE userId = :userId;
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId == principalId); // 1은 페이지 주인 -1은 주인 아님
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscibeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscibeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);

        return dto;
    }

    @Transactional
    public User 회원수정(int id, User user) {
        // 1. 영속화
        // 1. 무조건 찾았다. get() 2. 못찾았어 Exception 발동 orElseThrow()
        User userEntity = userRepository.findById(id).orElseThrow(() -> { return new CustomValidationApiException("찾을 수 없는 id입니다.");});
        System.out.println("===============================");
        userEntity.getImages().get(0);
        // 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());


        return userEntity;
    } // 더티체킹이 일어나서 업데이트가 완료됨
}
