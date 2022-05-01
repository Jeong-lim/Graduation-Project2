package com.cos.photogramstart.service;


import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User 회원수정(int id, User user) {
        // username, email 수정 불가
        User userEntity = userRepository.findById(id).get();

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
