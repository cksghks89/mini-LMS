package com.example.minilms.member.controller;

import com.example.minilms.common.model.ResponseResult;
import com.example.minilms.course.dto.TakeCourseDto;
import com.example.minilms.course.model.ServiceResult;
import com.example.minilms.course.model.TakeCourseInput;
import com.example.minilms.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ApiMemberController {
    private final TakeCourseService takeCourseService;

    @PostMapping("/api/member/course/cancel.api")
    public ResponseEntity<?> courseCancelReq(Model model
            , @RequestBody TakeCourseInput takeCourseInput
            , Principal principal
    ) {
        String userId = principal.getName();

        TakeCourseDto detail = takeCourseService.detail(takeCourseInput.getTakeCourseId());
        if(detail == null){
            ResponseResult responseResult = new ResponseResult(false, "수강신청 정보가 존재하지 않습니다.");
            return ResponseEntity.ok().body(responseResult);
        }

        if(!detail.getUserId().equals(userId)){
            ResponseResult responseResult = new ResponseResult(false, "본인의 수강 신청 정보만 취소할 수 있습니다.");
            return ResponseEntity.ok().body(responseResult);
        }

        ServiceResult result = takeCourseService.cancel(takeCourseInput.getTakeCourseId());
        if(!result.isResult()){
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());
            return ResponseEntity.ok().body(responseResult);
        }

        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }
}
