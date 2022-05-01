package com.cos.photogramstart.service;


import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void 회원프로필(int userId) {
        // SELECT * FROM image WHERE userId = userId;
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 프로프리 페이지는 없는 페이지 입니다.");
        });
    }

    @Transactional
    public User 회원수정(int id, User user) {
        // username, email 수정 불가
        // 1. 영속화
        // 무조건 찾았다. 걱정마 get() 2. 못찾았어 익섹션 발동시킬게 orElseThrow()
        User userEntity = userRepository.findById(id).orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 id입니다."));


        // 2. 영소고하된 오브젝트를 수정 - 더티 체킹
        userEntity.setName(user.getName());
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setGender(user.getGender());
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        // 비밀번호를 수정시 안넘기면 기존 비번 유지
        if(!user.getPassword().equals("")) {
            userEntity.setPassword(encPassword);
        }


        return userEntity;
    } // 더티체킹
}
