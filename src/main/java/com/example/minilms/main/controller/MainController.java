package com.example.minilms.main.controller;

import com.example.minilms.admin.dto.BannerDto;
import com.example.minilms.admin.entity.Banner;
import com.example.minilms.admin.repository.BannerRepository;
import com.example.minilms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        List<BannerDto> bannerList = bannerService.mainList();
        if(bannerList.isEmpty()){
            return "index";
        }

        bannerList.sort((x, y) -> (int) (y.getSortValue() - x.getSortValue()));

        model.addAttribute("bannerList", bannerList);

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied(){
        return "error/denied";
    }
}
