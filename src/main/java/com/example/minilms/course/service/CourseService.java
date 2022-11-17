package com.example.minilms.course.service;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseInput;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.model.TakeCourseInput;

import java.util.List;

public interface CourseService {
    boolean add(CourseInput parameter);

    List<CourseDto> list(CourseParam parameter);

    CourseDto getById(long id);

    boolean set(CourseInput parameter);

    /**
     * 강좌 내용 삭제
     */
    boolean delete(String idList);

    /**
     * 프론트 강좌 목록
     */
    List<CourseDto> frontList(CourseParam parameter);

    /**
     * 강좌 상세 정보
     */
    CourseDto frontDetail(long id);

    /**
     * 수강신청 request 요청
     */
    boolean req(TakeCourseInput takeCourseInput);
}
