package com.example.minilms.course.mapper;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.dto.TakeCourseDto;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.model.TakeCourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeCourseMapper {
    long selectListCount(TakeCourseParam parameter);
    List<TakeCourseDto> selectList(TakeCourseParam parameter);
    List<TakeCourseDto> selectListMyCourse(TakeCourseParam parameter);
}
