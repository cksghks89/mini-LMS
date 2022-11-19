package com.example.minilms.admin.service.impl;

import com.example.minilms.admin.entity.LoginHistory;
import com.example.minilms.admin.repository.LoginHistoryRepository;
import com.example.minilms.admin.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {
    private final LoginHistoryRepository loginHistoryRepository;

    @Override
    public boolean saveLog(String userId, String clientIp, String userAgent) {
        if (clientIp == null || userAgent == null) {
            return false;
        }

        loginHistoryRepository.save(LoginHistory.builder()
                .userId(userId)
                .clientIp(clientIp)
                .userAgent(userAgent)
                .loginDt(LocalDateTime.now())
                .build());

        return true;
    }
}
