package com.example.minilms.course.repository;

import com.example.minilms.course.entity.TakeCourse;
import com.example.minilms.course.type.TakeCourseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {
    long countByCourseIdAndUserIdAndStatusIn(long courseId, String userId, Collection<TakeCourseCode> status);
}
