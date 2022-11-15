package com.example.minilms.course.contoller;

import com.example.minilms.admin.dto.MemberDto;
import com.example.minilms.admin.model.MemberParam;
import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseInput;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.service.CourseService;
import com.example.minilms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController extends BaseController {
    private final CourseService courseService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam parameter) {

        parameter.init();

        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String pagerHtml = getPagerHtml(totalCount,
                parameter.getPageSize(),
                parameter.getPageIndex(),
                parameter.getQueryString()
        );

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", courseList);
        model.addAttribute("pager", pagerHtml);

        return "admin/course/list";
    }

    @GetMapping("/admin/course/add.do")
    public String add(Model model) {

        return "admin/course/add";
    }

    @PostMapping("/admin/course/add.do")
    public String addSubmit(Model model, CourseInput parameter) {
        boolean result = courseService.add(parameter);

        return "redirect:/admin/course/list.do";
    }
}
