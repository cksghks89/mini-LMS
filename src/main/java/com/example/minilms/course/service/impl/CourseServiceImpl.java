package com.example.minilms.course.service.impl;

import com.example.minilms.course.dto.CourseDto;
import com.example.minilms.course.entity.Course;
import com.example.minilms.course.entity.TakeCourse;
import com.example.minilms.course.mapper.CourseMapper;
import com.example.minilms.course.model.CourseInput;
import com.example.minilms.course.model.CourseParam;
import com.example.minilms.course.model.ServiceResult;
import com.example.minilms.course.model.TakeCourseInput;
import com.example.minilms.course.repository.CourseRepository;
import com.example.minilms.course.repository.TakeCourseRepository;
import com.example.minilms.course.service.CourseService;
import com.example.minilms.course.type.TakeCourseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final TakeCourseRepository takeCourseRepository;

    private LocalDate getLocalDate(String value){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try{
            return LocalDate.parse(value, formatter);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean add(CourseInput parameter) {
        LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());

        Course course = Course.builder()
                .subject(parameter.getSubject())
                .regDt(LocalDateTime.now())
                .keyword(parameter.getKeyword())
                .summary(parameter.getSummary())
                .contents(parameter.getContents())
                .price(parameter.getPrice())
                .salePrice(parameter.getSalePrice())
                .saleEndDt(saleEndDt)
                .categoryId(parameter.getCategoryId())
                .build();

        courseRepository.save(course);

        return true;
    }

    @Override
    public List<CourseDto> list(CourseParam parameter) {
        long totalCount = courseMapper.selectListCount(parameter);
        List<CourseDto> list = courseMapper.selectList(parameter);
        if(!CollectionUtils.isEmpty(list)){
            int i = 0;
            for(CourseDto x : list){
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        return list;
    }

    @Override
    public CourseDto getById(long id) {
        return courseRepository.findById(id)
                .map(CourseDto::of).orElse(null);
    }

    @Override
    public boolean set(CourseInput parameter) {
        Optional<Course> optionalCourse = courseRepository.findById(parameter.getId());
        if(!optionalCourse.isPresent()){
            return false;
        }
        LocalDate saleEndDt = getLocalDate(parameter.getSaleEndDtText());

        Course course = optionalCourse.get();
        course.setSubject(parameter.getSubject());
        course.setCategoryId(parameter.getCategoryId());
        course.setUdtDt(LocalDateTime.now());
        course.setKeyword(parameter.getKeyword());
        course.setSummary(parameter.getSummary());
        course.setContents(parameter.getContents());
        course.setPrice(parameter.getPrice());
        course.setSalePrice(parameter.getSalePrice());
        course.setSaleEndDt(saleEndDt);
        courseRepository.save(course);

        return true;
    }

    @Override
    public boolean delete(String idList) {
        if(idList == null) {
            return false;
        }

        String[] strList = idList.split(",");

        for(String id : strList){
            long longId = 0L;
            try{
                longId = Long.parseLong(id);
            }catch (Exception e){
                return false;
            }

            if(longId > 0){
                courseRepository.deleteById(longId);
            }
        }

        return true;
    }

    @Override
    public List<CourseDto> frontList(CourseParam parameter) {
        if(parameter.getCategoryId() < 1){
            List<Course> courseList = courseRepository.findAll();
            return CourseDto.of(courseList);
        }

        Optional<List<Course>> optionalCourses = courseRepository.findByCategoryId(parameter.getCategoryId());
        return optionalCourses.map(CourseDto::of).orElse(null);
    }

    @Override
    public CourseDto frontDetail(long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        return optionalCourse.map(CourseDto::of).orElse(null);
    }

    @Override
    public ServiceResult req(TakeCourseInput takeCourseInput) {
        // takeCourseInput : 이메일, 강좌정보(id값)
        Optional<Course> optionalCourse = courseRepository.findById(takeCourseInput.getCourseId());
        if(!optionalCourse.isPresent()){
            return ServiceResult.builder()
                    .result(false)
                    .message("강좌 정보가 존재하지 않습니다.")
                    .build();
        }

        Course course = optionalCourse.get();

        // 신청 정보가 있는지 확인 (validation)
        long count = takeCourseRepository.countByCourseIdAndUserIdAndStatusIn(course.getId(),
                takeCourseInput.getUserId(), List.of(TakeCourseCode.REQ, TakeCourseCode.COMPLETE));
        if(count > 0){
            return ServiceResult.builder()
                    .result(false)
                    .message("이미 신청한 강좌 정보가 존재합니다.")
                    .build();
        }

        TakeCourse takeCourse = TakeCourse.builder()
                .courseId(course.getId())
                .userId(takeCourseInput.getUserId())
                .payPrice(course.getSalePrice())
                .regDt(LocalDateTime.now())
                .status(TakeCourseCode.REQ)
                .build();
        takeCourseRepository.save(takeCourse);

        return ServiceResult.builder()
                .result(true)
                .message("수강신청에 성공하였습니다.")
                .build();
    }
}
