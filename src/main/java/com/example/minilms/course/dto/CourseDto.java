package com.example.minilms.course.dto;

import com.example.minilms.course.entity.Course;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    Long id;
    String imagePath;
    String keyword;
    String subject;
    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;
    LocalDateTime regDt;    // 등록일
    LocalDateTime udtDt;    // 수정일
    long categoryId;

    long totalCount;
    long seq;

    String fileName;
    String urlFileName;

    public static CourseDto of(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .imagePath(course.getImagePath())
                .keyword(course.getKeyword())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndDt(course.getSaleEndDt())
                .regDt(course.getRegDt())
                .udtDt(course.getUdtDt())
                .categoryId(course.getCategoryId())

                .fileName(course.getFileName())
                .urlFileName(course.getUrlFileName())
                .build();
    }

    public static List<CourseDto> of(List<Course> courseList) {
        if (courseList == null) {
            return null;
        }

        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course x : courseList) {
            courseDtoList.add(CourseDto.of(x));
        }

        return courseDtoList;
    }
}
