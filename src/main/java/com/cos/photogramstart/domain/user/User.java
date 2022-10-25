package com.cos.photogramstart.domain.user;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data // getter setter
@Entity // DB에 테이블을 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id; // 자동 증가

    @Column(unique = true)
    private String username;
    private String password;

    private String name;
    private String website; // 웹사이트
    private String bio; // 자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // User 사진
    private String role; // 권한

    private LocalDateTime createDate; // 얘가 언제 들어왔는지 자동으로 들어가게

    @PrePersist // DB에 INSERT되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
