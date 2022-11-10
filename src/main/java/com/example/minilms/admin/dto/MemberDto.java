package com.example.minilms.admin.dto;

import com.example.minilms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    String userStatus;

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .phoneNumber(member.getPhoneNumber())
                .regDt(member.getRegDt())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthDt(member.getEmailAuthDt())
                .emailAuthKey(member.getEmailAuthKey())
                .resetPasswordKey(member.getResetPasswordKey())
                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
                .adminYn(member.isAdminYn())
                .userStatus(member.getUserStatus())
                .build();
    }
}
