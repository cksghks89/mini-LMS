package com.example.minilms.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Slf4j
@RequiredArgsConstructor
@Component
public class MailComponents {
    private final JavaMailSender javaMailSender;

    public void sendMailTest(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("song960705@naver.com");
        msg.setSubject("Test mail");
        msg.setText("JavaMailSender Test Mail");

        javaMailSender.send(msg);
    }

    public void sendMail(String mail, String subject, String text){
        boolean result = false;
        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper =
                        new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        try{
            javaMailSender.send(msg);
            result = true;
        }catch (Exception e){
            log.error("메일 발송 실패", e);
        }

    }
}
