package com.example.minilms.course.contoller;

import com.example.minilms.admin.service.CategoryService;
import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseInput;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCourseController extends BaseController {
    private final CourseService courseService;
    private final CategoryService categoryService;

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

    @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(Model model, HttpServletRequest request,
                      CourseInput parameter) {
        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if (editMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existCourse;
        }

        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);

        return "admin/course/add";
    }

    @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model,
                            HttpServletRequest request,
                            CourseInput parameter) {
        boolean editMode = request.getRequestURI().contains("/edit.do");
        if (editMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean reuslt = courseService.set(parameter);
        } else {
            boolean result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/admin/course/delete.do")
    public String delete(Model model, CourseInput parameter) {
        boolean result = courseService.delete(parameter.getIdList());

        return "redirect:/admin/course/list.do";
    }
}
