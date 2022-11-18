package com.example.minilms.course.model;

import lombok.Data;

@Data
public class TakeCourseInput {
    long courseId;
    String userId;

    long takeCourseId;
}
