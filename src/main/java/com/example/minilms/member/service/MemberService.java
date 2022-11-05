package com.example.minilms.member.service;

import com.example.minilms.member.model.MemberInput;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService{
    boolean register(MemberInput memberInput);

    boolean emailAuth(String uuid);
}
