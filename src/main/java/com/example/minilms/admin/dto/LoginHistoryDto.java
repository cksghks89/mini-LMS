package com.example.minilms.admin.dto;

import com.example.minilms.admin.entity.LoginHistory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class LoginHistoryDto {
    private long id;

    private String userId;
    private String userAgent;
    private String clientIp;
    private LocalDateTime loginDt;

    public String getLoginDtText(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(dateTimeFormatter) : "";
    }

    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .userId(loginHistory.getUserId())
                .userAgent(loginHistory.getUserAgent())
                .clientIp(loginHistory.getClientIp())
                .loginDt(loginHistory.getLoginDt())
                .build();
    }

    public static List<LoginHistoryDto> of(List<LoginHistory> allByUserId) {
        return allByUserId.stream().map(LoginHistoryDto::of).collect(Collectors.toList());
    }
}
