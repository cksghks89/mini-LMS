package com.example.minilms.course.model;

import com.example.minilms.admin.model.CommonParam;
import lombok.*;

@Data
public class CourseParam extends CommonParam {
    long id;
    long categoryId;
}
