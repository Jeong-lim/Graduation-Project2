package com.cos.photogramstart.domain.subscribe;


import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// 중간 테이블

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscibe_uk",
                        columnNames = {"fromUserId", "toUserId"}
                )
        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "fromUserId") // 이렇게 컬럼명 만들어!
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "toUserId") // 오브젝트일때는 네이티브 쿼리를 써야된다.
    @ManyToOne
    private User toUser;

    private LocalDateTime createDate;


    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }


}
