package com.example.minilms.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member implements MemberCode{
    @Id
    private String userId;

    private String userName;
    private String phoneNumber;
    private String password;

    private LocalDateTime regDt;

    private boolean emailAuthYn;
    private String emailAuthKey;
    private LocalDateTime emailAuthDt;

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private boolean adminYn;

    // 이용 가능한 상태, 정지 상태
    private String userStatus;
}
