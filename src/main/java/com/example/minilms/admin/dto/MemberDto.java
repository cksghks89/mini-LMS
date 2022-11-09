package com.example.minilms.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    String userId;
    String userName;
    String phoneNumber;
    String password;

    LocalDateTime regDt;

    boolean emailAuthYn;
    String emailAuthKey;
    LocalDateTime emailAuthDt;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitDt;

    boolean adminYn;

    // 추가 컬럼
    long totalCount;
    long seq;
}
