package com.example.minilms.course.service.impl;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.dto.TakeCourseDto;
import com.example.minilms.course.entity.TakeCourse;
import com.example.minilms.course.mapper.CourseMapper;
import com.example.minilms.course.mapper.TakeCourseMapper;
import com.example.minilms.course.model.ServiceResult;
import com.example.minilms.course.model.TakeCourseParam;
import com.example.minilms.course.repository.CourseRepository;
import com.example.minilms.course.repository.TakeCourseRepository;
import com.example.minilms.course.service.TakeCourseService;
import com.example.minilms.course.type.TakeCourseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TakeCourseServiceImpl implements TakeCourseService {
    private final TakeCourseMapper takeCourseMapper;
    private final TakeCourseRepository takeCourseRepository;

    @Override
    public List<TakeCourseDto> list(TakeCourseParam parameter) {
        long totalCount = takeCourseMapper.selectListCount(parameter);
        List<TakeCourseDto> list = takeCourseMapper.selectList(parameter);
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(TakeCourseDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public ServiceResult updateStatus(long id, TakeCourseCode status) {
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if(!optionalTakeCourse.isPresent()){
            return new ServiceResult(false, "수강정보가 존재하지 않습니다.");
        }
        TakeCourse takeCourse = optionalTakeCourse.get();
        takeCourse.setStatus(status);

        takeCourseRepository.save(takeCourse);

        return new ServiceResult(true);
    }

    @Override
    public List<TakeCourseDto> myCourse(String userId) {
        TakeCourseParam parameter = new TakeCourseParam();
        parameter.setUserId(userId);
        return takeCourseMapper.selectListMyCourse(parameter);
    }
}
