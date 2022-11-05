package com.example.minilms.member.controller;

import com.example.minilms.member.entity.Member;
import com.example.minilms.member.model.MemberInput;
import com.example.minilms.member.repository.MemberRepository;
import com.example.minilms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response
            , MemberInput memberInput) {

        boolean result = memberService.register(memberInput);
        model.addAttribute("result", result);

        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String registerAuth(
            Model model,
            @RequestParam("id") String id
    ) {
        boolean result = memberService.emailAuth(id);

        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(){
        return "member/info";
    }
}
