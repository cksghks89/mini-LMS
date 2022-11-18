package com.example.minilms.course.service;

import com.example.minilms.course.dto.TakeCourseDto;
import com.example.minilms.course.model.ServiceResult;
import com.example.minilms.course.model.TakeCourseParam;
import com.example.minilms.course.type.TakeCourseCode;

import java.util.List;

public interface TakeCourseService {
    List<TakeCourseDto> list(TakeCourseParam parameter);

    /**
     * 수강신청 상태변경
     */
    ServiceResult updateStatus(long id, TakeCourseCode status);

    /**
     * 회원 수강 내역 목록
     */
    List<TakeCourseDto> myCourse(String userId);
}
