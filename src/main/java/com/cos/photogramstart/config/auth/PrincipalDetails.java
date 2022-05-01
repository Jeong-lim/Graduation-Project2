package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private User user;

    public PrincipalDetails(User user) { // 권한은 유저가 들고 있음
        this.user = user;
    }

    // 권한: 한개가 아닐 수 있음(3개 이상의 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한을 가져오는 함수
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole();});
        return collector;
    } // 자바는 매개변수에 함수를 넣지 못한다.

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    // 원래는 신경을 써줘야되는 부분이다. 하지만 지금은 X
}
