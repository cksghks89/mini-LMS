package com.example.minilms.admin.service;

import com.example.minilms.admin.dto.BannerDto;
import com.example.minilms.admin.model.BannerParam;
import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> list(BannerParam parameter);

    BannerDto getById(long id);
}
