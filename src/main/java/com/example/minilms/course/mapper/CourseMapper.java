package com.example.minilms.course.mapper;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    long selectListCount(CourseParam parameter);
    List<CourseDto> selectList(CourseParam parameter);
}
