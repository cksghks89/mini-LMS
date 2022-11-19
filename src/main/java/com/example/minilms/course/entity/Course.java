package com.example.minilms.course.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long categoryId;

    String imagePath;
    String keyword;
    String subject;

    @Column(length = 1000)
    String summary;

    @Lob
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;

    LocalDateTime regDt;    // 등록일
    LocalDateTime udtDt;    // 수정일

    String fileName;
    String urlFileName;
}
