package com.example.minilms.main.controller;

import com.example.minilms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/sendMailTest")
    public void sendMailTest(){
        mailComponents.sendMailTest();
    }

    @GetMapping("/sendMail")
    public void sendMail(){
        String mail = "song960705@naver.com";
        String subject = "HTML mail Test";
        String text = "JavaMailSender HTML test Mail";

        mailComponents.sendMail(mail, subject, text);
    }

    @RequestMapping("/error/denied")
    public String errorDenied(){
        return "error/denied";
    }
}
