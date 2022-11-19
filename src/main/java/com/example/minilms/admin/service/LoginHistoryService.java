package com.example.minilms.admin.service;

public interface LoginHistoryService {
    public boolean saveLog(String userId, String clientIp, String userAgent);
}
