package com.cos.shareHere.service;

import com.cos.shareHere.domain.user.User;
import com.cos.shareHere.domain.user.UserRepository;
import com.cos.shareHere.handler.ex.CustomValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Transactional
    public User 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        User findUsername = userRepository.findByUsername(user.getUsername());
        if (findUsername == null) {
            User userEntity = userRepository.save(user);
            return userEntity;
        } else {
            throw new CustomValidationException("이미 존재하는 아이디입니다.", null);
        }
    }
}
