package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
