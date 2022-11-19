package com.example.minilms.config;

import com.example.minilms.admin.service.LoginHistoryService;
import com.example.minilms.member.service.MemberService;
import com.example.minilms.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final LoginHistoryService loginHistoryService;
    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userAgent = RequestUtils.getUserAgent(request);
        String clientIp = RequestUtils.getClientIp(request);
        String userId = authentication.getName();

        memberService.updateLoginHistory(userId);

        boolean result = loginHistoryService.saveLog(userId, clientIp, userAgent);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}