package com.cos.shareHere.web.dto.user;

import com.cos.shareHere.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {
    // 필수로 받아야 하는 데이터
    @NotBlank
    private String name;

    private String password;

    // 받지 않아도 되는 데이터
    private String website;
    private String bio;
    private String phone;
    private String gender;

    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
