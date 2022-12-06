package com.yonduuniversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduuniversity.rohan.models.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
