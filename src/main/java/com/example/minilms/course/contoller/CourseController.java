package com.example.minilms.course.contoller;

import com.example.minilms.admin.dto.CategoryDto;
import com.example.minilms.admin.service.CategoryService;
import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/course")
    public String course(Model model, CourseParam courseParam) {

        List<CourseDto> list = courseService.frontList(courseParam);
        model.addAttribute("list", list);

        int courseTotalCount = 0;
        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if(categoryList != null){
            for(CategoryDto x : categoryList){
                courseTotalCount += x.getCourseCount();
            }
        }

        model.addAttribute("courseTotalCount", courseTotalCount);
        model.addAttribute("categoryList", categoryList);

        return "course/index";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(Model model, CourseParam courseParam) {
        CourseDto courseDto = courseService.frontDetail(courseParam.getId());
        model.addAttribute("detail", courseDto);
        return "course/detail";
    }
}
