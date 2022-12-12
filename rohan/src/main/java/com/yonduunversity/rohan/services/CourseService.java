package com.yonduunversity.rohan.services;

import java.util.List;

import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.dto.CourseDTO;

public interface CourseService {
    Course deactivateCourse(String code);
    Course addCourse(Course course);
    List<Course> getCourses(int pageNumber, int pageSize);
    Course getCourse(String code);
    List<CourseDTO>getCourseByKeyword(String keyword, int pageNumber, int pageSize);
}
