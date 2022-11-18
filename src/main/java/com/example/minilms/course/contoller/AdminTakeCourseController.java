package com.example.minilms.course.contoller;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.dto.TakeCourseDto;
import com.example.minilms.course.model.ServiceResult;
import com.example.minilms.course.model.TakeCourseParam;
import com.example.minilms.course.service.CourseService;
import com.example.minilms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminTakeCourseController extends BaseController {
    private final TakeCourseService takeCourseService;
    private final CourseService courseService;

    @GetMapping("/admin/takecourse/list.do")
    public String list(Model model, TakeCourseParam parameter,
                       BindingResult bindingResult) {
        parameter.init();

        List<TakeCourseDto> list = takeCourseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(list)) {
            totalCount = list.get(0).getTotalCount();
        }
        String pagerHtml = getPagerHtml(totalCount,
                parameter.getPageSize(),
                parameter.getPageIndex(),
                parameter.getQueryString()
        );

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", list);
        model.addAttribute("pager", pagerHtml);

        List<CourseDto> courseList = courseService.listAll();
        model.addAttribute("courseList", courseList);

        return "admin/takecourse/list";
    }

    @PostMapping("/admin/takecourse/status.do")
    public String status(Model model, TakeCourseParam parameter) {

        ServiceResult result = takeCourseService.updateStatus(parameter.getId(), parameter.getStatus());
        if (!result.isResult()) {
            model.addAttribute("message", result.getMessage());
            return "/common/error";
        }
        return "redirect:/admin/takecourse/list.do";
    }

}
