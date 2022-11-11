package com.cos.shareHere.web.dto.user;

import com.cos.shareHere.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private boolean pageOwnerState; // 이 페이지의 주인인가 아닌가
    private int imageCount;
    private boolean subscribeState;
    private int subscribeCount;
    private User user;
}
