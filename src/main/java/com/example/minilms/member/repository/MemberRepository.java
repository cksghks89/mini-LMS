package com.example.minilms.member.repository;

import com.example.minilms.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmailAuthKey(String emailAuthKey);
}
