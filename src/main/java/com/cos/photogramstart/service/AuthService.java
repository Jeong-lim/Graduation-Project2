package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service // 1. IOC 2. 트랜젝션 관리
public class AuthService {

    private final UserRepository userRepository;

    @Transactional // Write(Insert, Update, Delete)
    public User 회원가입(User user) { // 여기서의 user는 외부 통신을 통해 받은 데이터를 user 오브젝트에 담은 것
        // 회원가입 진행
        User userEntity = userRepository.save(user); // 여기의 userEntity는 데이터베이스에 있는 데이터를 user 오브젝트에 담은 것
        return userEntity;

    }
}
