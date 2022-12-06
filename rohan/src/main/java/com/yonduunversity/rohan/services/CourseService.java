package com.yonduunversity.rohan.services;

import java.util.List;

import com.yonduunversity.rohan.models.Course;

public interface CourseService {

    Course createCourse(int courseCode, String title, String description);

    List<Course> retrieveCourses(int pageNumber, int pageSize);

    Course retrieveCourse(int courseCode);

    List<Course> searchCourse(String info);

    void deactivateCourse(int courseCode);
}
