package com.example.minilms.admin.dto;

import com.example.minilms.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
    LocalDateTime updateDt;

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

    public String getRegDtText(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? regDt.format(dateTimeFormatter) : "";
    }

    public String getUpdateDt(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return updateDt != null ? updateDt.format(dateTimeFormatter) : "";
    }

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
                .updateDt(member.getUpdateDt())
                .build();
    }
}
