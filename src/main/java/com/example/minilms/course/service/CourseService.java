package com.example.minilms.course.service;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseInput;
import com.example.minilms.course.model.CourseParam;

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
}
