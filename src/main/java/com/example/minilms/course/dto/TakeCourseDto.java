package com.example.minilms.course.dto;

import com.example.minilms.course.type.TakeCourseCode;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TakeCourseDto {
    Long id;
    long courseId;
    String userId;

    long payPrice; // 결제금액
    TakeCourseCode status; // 상태(수강신청, 결제완료, 수강취소)
    LocalDateTime regDt; // 신청일

    // JOIN COLUMN
    String userName;
    String phoneNumber;
    String subject;

    long totalCount;
    long seq;

    public String getRegDtText(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? this.regDt.format(dtf) : "";
    }
}
