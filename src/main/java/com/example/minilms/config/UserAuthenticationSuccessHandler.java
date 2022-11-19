package com.example.minilms.config;

import com.example.minilms.admin.repository.CategoryRepository;
import com.example.minilms.admin.repository.LoginHistoryRepository;
import com.example.minilms.admin.service.CategoryService;
import com.example.minilms.admin.service.LoginHistoryService;
import com.example.minilms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userAgent = RequestUtils.getUserAgent(request);
        String clientIp = RequestUtils.getClientIp(request);
        String userId = authentication.getName();

        boolean result = loginHistoryService.saveLog(userId, clientIp, userAgent);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}