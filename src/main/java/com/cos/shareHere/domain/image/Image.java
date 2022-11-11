package com.cos.shareHere.domain.image;


import com.cos.shareHere.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image { // N, 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String caption; // 사진 설명
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정 폴더에 저장 -> DB에 저장된 경로를 insert

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user; // 1, 2

    // 이미지 좋아요

    // 댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    // 오브젝트를 콘솔에 출력할 떄 문제가 될 수 있어서 User 부분을 출력되지 않게 함
/*    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }*/
}