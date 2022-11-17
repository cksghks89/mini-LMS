package com.example.minilms.course.entity;

import com.example.minilms.course.type.TakeCourseCode;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TakeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long courseId;
    String userId;

    long payPrice; // 결제금액

    @Enumerated(EnumType.STRING)
    TakeCourseCode status; // 상태(수강신청, 결제완료, 수강취소)

    LocalDateTime regDt; // 신청일
}
