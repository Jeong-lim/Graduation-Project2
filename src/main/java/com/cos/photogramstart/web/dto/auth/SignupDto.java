package com.cos.photogramstart.web.dto.auth;

// 요청하는 DTO 통신할 때 필요한것을 담는

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
    private String username;
    private String password;
    private String email;
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
