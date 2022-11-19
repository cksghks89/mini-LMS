package com.example.minilms.admin.service;

import com.example.minilms.admin.dto.LoginHistoryDto;
import com.example.minilms.admin.model.MemberParam;

import java.util.List;

public interface LoginHistoryService {
    boolean saveLog(String userId, String clientIp, String userAgent);
}
