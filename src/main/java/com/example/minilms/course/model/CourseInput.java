package com.example.minilms.course.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInput {
    String subject;
    long id;
    long categoryId;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    // 삭제를 위한 속성
    String idList;

    // ADD
    String fileName;
    String urlFileName;
}
