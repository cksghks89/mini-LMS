package com.example.minilms.admin.controller;

import com.example.minilms.admin.dto.BannerDto;
import com.example.minilms.admin.model.BannerInput;
import com.example.minilms.admin.model.BannerParam;
import com.example.minilms.admin.service.BannerService;
import com.example.minilms.course.contoller.BaseController;
import com.example.minilms.course.model.CourseInput;
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
public class AdminBannerController extends BaseController {
    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {
        List<BannerDto> bannerList = bannerService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(bannerList)) {
            totalCount = bannerList.get(0).getTotalCount();
        }
        String pagerHtml = getPagerHtml(totalCount,
                parameter.getPageSize(),
                parameter.getPageIndex(),
                parameter.getQueryString()
        );

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", bannerList);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model, HttpServletRequest request,
                      BannerInput parameter) {
        boolean editMode = request.getRequestURI().contains("/edit.do");

        BannerDto detail = new BannerDto();

        if (editMode) {
            long id = parameter.getId();
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
        }

        model.addAttribute("detail", detail);
        model.addAttribute("editMode", editMode);

        return "admin/banner/add";
    }

    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addSubmit(Model model,
                            HttpServletRequest request,
                            BannerInput parameter,
                            MultipartFile file) {
        String saveFileName = "";
        String urlFileName = "";

        if (!file.isEmpty()) {
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
            BannerDto existBanner = bannerService.getById(id);
            if (existBanner == null) {
                model.addAttribute("message", "배너정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean reuslt = bannerService.set(parameter);
        } else {
            boolean result = bannerService.add(parameter);
        }

        return "redirect:/admin/banner/list.do";
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

    @PostMapping("/admin/banner/delete.do")
    public String delete(Model model, BannerInput parameter) {
        boolean result = bannerService.delete(parameter.getIdList());

        return "redirect:/admin/banner/list.do";
    }
}
