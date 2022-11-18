package com.example.minilms.member.controller;

import com.example.minilms.admin.dto.MemberDto;
import com.example.minilms.course.model.ServiceResult;
import com.example.minilms.member.entity.Member;
import com.example.minilms.member.model.MemberInput;
import com.example.minilms.member.model.ResetPasswordInput;
import com.example.minilms.member.repository.MemberRepository;
import com.example.minilms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find_password";
    }

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/find/password")
    public String findPasswordSubmit(
            Model model,
            ResetPasswordInput resetPasswordInput
    ) {
        boolean result = memberService.sendResetPassword(resetPasswordInput);
        model.addAttribute("result", result);

        return "member/find_password_result";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model, @RequestParam("id") String uuid) {
        boolean result = memberService.checkResetPassword(uuid);

        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(Model model, ResetPasswordInput resetPasswordInput) {
        boolean result = false;

        try {
            result = memberService.resetPassword(resetPasswordInput.getId(), resetPasswordInput.getPassword());
        } catch (Exception e) {

        }
        model.addAttribute("result", result);

        return "member/reset_password_result";
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
    public String memberInfo(Model model, Principal principal) {
        String userId = principal.getName();
        MemberDto detail = memberService.detail(userId);

        model.addAttribute("detail", detail);

        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(Model model, MemberInput memberInput,
                                   Principal principal) {
        String userId = principal.getName();
        memberInput.setUserId(userId);

        ServiceResult serviceResult = memberService.updateMember(memberInput);
        if(!serviceResult.isResult()){
            model.addAttribute("message", serviceResult.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword() {
        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(Model model,
                                       MemberInput memberInput,
                                       Principal principal) {

        String userId = principal.getName();
        memberInput.setUserId(userId);
        ServiceResult serviceResult = memberService.updateMemberPassword(memberInput);
        if(!serviceResult.isResult()){
            model.addAttribute("message", serviceResult.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/takecourse")
    public String takeCourse() {
        return "member/takecourse";
    }
}
