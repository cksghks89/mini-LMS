package com.example.minilms.course.contoller;

import com.example.minilms.course.model.TakeCourseInput;
import com.example.minilms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiCourseController {

    private final CourseService courseService;

    @PostMapping("/api/course/req.api")
    public ResponseEntity<?> courseReq(
            @RequestBody TakeCourseInput takeCourseInput,
            Principal principal){
        takeCourseInput.setUserId(principal.getName());

        boolean result = courseService.req(takeCourseInput);
        if(!result){
            return ResponseEntity.badRequest().body("수강신청에 실패하였습니다.");
        }

        return ResponseEntity.ok().body(takeCourseInput);
    }
}
