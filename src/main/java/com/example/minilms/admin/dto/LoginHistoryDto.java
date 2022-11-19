package com.example.minilms.admin.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LoginHistoryDto {
    private long id;

    private String userId;
    private String userAgent;
    private String clientIp;
    private LocalDateTime loginDt;
}
