package com.example.minilms.member.service.impl;

import com.example.minilms.components.MailComponents;
import com.example.minilms.member.entity.Member;
import com.example.minilms.member.exception.MemberNotEmailAuthException;
import com.example.minilms.member.model.MemberInput;
import com.example.minilms.member.repository.MemberRepository;
import com.example.minilms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

    /**
     * 회원 가입
     */
    @Override
    public boolean register(MemberInput memberInput) {
        Optional<Member> optionalMember = memberRepository.findById(memberInput.getUserId());
        if (optionalMember.isPresent()) {
            // 회원이 이미 존재함
            return false;
        }

        String encPassword = BCrypt.hashpw(memberInput.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .userId(memberInput.getUserId())
                .userName(memberInput.getUserName())
                .phoneNumber(memberInput.getPhoneNumber())
                .password(encPassword)
                .regDt(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(uuid)
                .build();

        memberRepository.save(member);

        String email = memberInput.getUserId();
        String subject = "LMS 가입을 축하드립니다.";
        String text = "<p>사이트 가입을 축하드립니다. </p>" +
                "<p>아래 링크를 눌러 가입을 완료하세요</p>" +
                "<div><a = href='http://localhost:8080/member/email-auth?id="
                + uuid + "'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean emailAuth(String uuid) {
        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
        if(!optionalMember.isPresent()){
            return false;
        }

        Member member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(username);
        if(!optionalMember.isPresent()){
            throw new UsernameNotFoundException("회원 정보가 없습니다.");
        }

        Member member = optionalMember.get();

        if(!member.isEmailAuthYn()){
            throw new MemberNotEmailAuthException("이메일 활성화 이후에 로그인을 해 주세요.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);
    }
}
