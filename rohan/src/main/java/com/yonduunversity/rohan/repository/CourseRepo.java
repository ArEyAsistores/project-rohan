package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Long> {
    Course findByCourseCode(long name);

    @Query("SELECT p FROM Course p WHERE cast(p.courseCode as string ) LIKE %?1%"
            + " OR p.title LIKE %?1%"
            + " OR p.description LIKE %?1%")
    List<Course> findAllByKeyword(String keyword);
}
