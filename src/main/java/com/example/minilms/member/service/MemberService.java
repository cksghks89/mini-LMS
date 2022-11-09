package com.example.minilms.member.service;

import com.example.minilms.admin.dto.MemberDto;
import com.example.minilms.admin.model.MemberParam;
import com.example.minilms.member.entity.Member;
import com.example.minilms.member.model.MemberInput;
import com.example.minilms.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService{
    /**
     * 회원가입 및 가입활성화 이메일 전송
     */
    boolean register(MemberInput memberInput);

    /**
     * uuid에 해당하는 계정을 활성화 함
     */
    boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 전보를 전송
     */
    boolean sendResetPassword(ResetPasswordInput resetPasswordInput);

    /**
     * 입력받은 uuid에 대해서 password로 초기화 진행
     */
    boolean resetPassword(String uuid, String password);

    /**
     * 입력받은 uuid 값이 유효한지 확인
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원의 목록을 리턴 (관리자에서만 사용 가능)
     */
    List<MemberDto> list(MemberParam parameter);
}
