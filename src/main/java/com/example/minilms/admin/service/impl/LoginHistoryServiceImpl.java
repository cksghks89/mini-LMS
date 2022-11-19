package com.example.minilms.admin.service.impl;

import com.example.minilms.admin.dto.LoginHistoryDto;
import com.example.minilms.admin.entity.LoginHistory;
import com.example.minilms.admin.model.MemberParam;
import com.example.minilms.admin.repository.LoginHistoryRepository;
import com.example.minilms.admin.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<LoginHistoryDto> history(String userId) {
        Optional<List<LoginHistory>> allByUserId = loginHistoryRepository.findAllByUserIdOrderByLoginDtDesc(userId);
        if(allByUserId.isEmpty()){
            return new ArrayList<>();
        }

        return LoginHistoryDto.of(allByUserId.get());
    }
}
