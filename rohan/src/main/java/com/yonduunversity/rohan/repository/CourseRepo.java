package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Course findCourseByCode(String code);

    @Query("SELECT p FROM Course p WHERE p.code LIKE %?1%"
            + " OR p.title LIKE %?1%"
            + " OR p.description LIKE %?1%")
    List<Course> findAllByKeyword(String keyword);
}