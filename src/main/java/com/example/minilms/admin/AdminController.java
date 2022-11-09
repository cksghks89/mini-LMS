package com.example.minilms.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/main.do")
    public String main(){
        return "admin/main";
    }
}
