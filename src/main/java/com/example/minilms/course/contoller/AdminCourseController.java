package com.example.minilms.course.contoller;

import com.example.minilms.admin.service.CategoryService;
import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.model.CourseInput;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
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
                            CourseInput parameter,
                            MultipartFile file) {
        String saveFileName = "";
        String urlFileName = "";

        if (file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            String baseLocalPath = "C:\\Users\\songkey2\\Documents\\Zero-base\\zerobase_practice_Project\\miniLMS\\files";
            String baseUrlPath = "/files";

            String[] arrFileName = getNewSaveFile(baseLocalPath, baseUrlPath, originalFileName);
            saveFileName = arrFileName[0];
            urlFileName = arrFileName[1];

            try {
                File newFile = new File(saveFileName);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info("############################ - 1");
                log.info(e.getMessage());
            }
        }

        parameter.setFileName(saveFileName);
        parameter.setUrlFileName(urlFileName);

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

    private String[] getNewSaveFile(String basePath, String baseUrlPath, String originalFileName) {
        LocalDate now = LocalDate.now();

        String[] dirs = {
                String.format("%s/%d/", basePath, now.getYear()),
                String.format("%s/%d/%02d/", basePath, now.getYear(), now.getMonthValue()),
                String.format("%s/%d/%02d/%02d/", basePath, now.getYear(), now.getMonthValue(), now.getDayOfMonth())
        };

        String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        for (String dir : dirs) {
            File file = new File(dir);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }
        String fileExtension = "";
        if (originalFileName != null) {
            int dotPos = originalFileName.lastIndexOf(".");
            if (dotPos > -1) {
                fileExtension = originalFileName.substring(dotPos + 1);
            }
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = String.format("%s%s", dirs[2], uuid);
        String newUrlFileName = String.format("%s%s", urlDir, uuid);
        if (fileExtension.length() > 0) {
            newFileName += "." + fileExtension;
            newUrlFileName += "." + fileExtension;
        }

        return new String[]{newFileName, newUrlFileName};
    }

    @PostMapping("/admin/course/delete.do")
    public String delete(Model model, CourseInput parameter) {
        boolean result = courseService.delete(parameter.getIdList());

        return "redirect:/admin/course/list.do";
    }
}
