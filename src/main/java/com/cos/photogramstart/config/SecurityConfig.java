package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 시큐리티를 활성화
@Configuration // IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // SecurityConfig가 IoC에 등록될 때 Bean 어노테이션을 읽어서 return을 해서 IoC가 들고 있는다.
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화 됨
        // 인증이 되지 않은 사용자는 모두 login으로 빠지게
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
                .anyRequest().permitAll() // 403은 승인이 되지 않은 사용자
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/");

        // 403이 뜨는 페이지가 떳을 때 login 창으로 바로 넘어가게
    }

}
