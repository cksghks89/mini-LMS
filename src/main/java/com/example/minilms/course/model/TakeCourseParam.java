package com.example.minilms.course.model;

import com.example.minilms.admin.model.CommonParam;
import com.example.minilms.course.type.TakeCourseCode;
import lombok.Data;

@Data
public class TakeCourseParam extends CommonParam {
    long id;
    TakeCourseCode status;
}
