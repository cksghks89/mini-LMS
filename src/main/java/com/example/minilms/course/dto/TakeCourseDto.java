package com.example.minilms.course.dto;

import com.example.minilms.course.entity.TakeCourse;
import com.example.minilms.course.type.TakeCourseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public static TakeCourseDto of(TakeCourse takeCourse) {
        return TakeCourseDto.builder()
                .id(takeCourse.getId())
                .courseId(takeCourse.getCourseId())
                .userId(takeCourse.getUserId())
                .payPrice(takeCourse.getPayPrice())
                .status(takeCourse.getStatus())
                .regDt(takeCourse.getRegDt())
                .build();
    }

    public String getRegDtText(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return regDt != null ? this.regDt.format(dtf) : "";
    }
}
