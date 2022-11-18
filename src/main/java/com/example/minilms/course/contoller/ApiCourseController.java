package com.example.minilms.course.contoller;

import com.example.minilms.common.model.ResponseResult;
import com.example.minilms.course.model.ServiceResult;
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

        ServiceResult result = courseService.req(takeCourseInput);
        if(!result.isResult()){
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }

        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }

}
