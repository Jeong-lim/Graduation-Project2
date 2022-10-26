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

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 권한 : 한개가 아닐 수 있음 (3개 이상의 권한일 수도)
   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole();}); // 자바에서는 함수를 바로 못넣음
        return collector;
    } // 권한을 가져오는 함수 User의 role

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
}
