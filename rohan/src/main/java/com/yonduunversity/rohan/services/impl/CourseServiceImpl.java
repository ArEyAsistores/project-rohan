package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.dto.CourseDTO;
import com.yonduunversity.rohan.repository.CourseRepo;
import com.yonduunversity.rohan.repository.pagination.CourseRepoPaginate;
import com.yonduunversity.rohan.services.ClassService;
import com.yonduunversity.rohan.services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepoPaginate courseRepoPaginate;
    private final CourseRepo courseRepo;

    @Override
    public List<CourseDTO> getCourseByKeyword(String keyword, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<CourseDTO> pagedResult = courseRepoPaginate.findAll(paging).map(CourseDTO::new);
        if (keyword != null) {
            return courseRepoPaginate.findAllByKeyword(keyword,paging).stream().map(CourseDTO::new).collect(Collectors.toList());
        } else {
            return  pagedResult.stream().collect(Collectors.toList());
        }
    }

    @Override
    public Course deactivateCourse(String code) {
        Course course = courseRepo.findCourseByCode(code);
        if (course.isActive()) {
            course.setActive(false);
        }
        return course;
    }
    @Override
    public Course getCourse(String code) {
        return courseRepo.findCourseByCode(code);
    }

    @Override
    public List<Course> getCourses(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<Course> pagedResult = courseRepoPaginate.findAll(paging);
        return pagedResult.stream().toList();
    }

    @Override
    public Course addCourse(Course course) {
        log.info("{} added to Database", course.getTitle());
        log.info("{} added to Database", course.getCode());
        course.setCode(course.getCode().toUpperCase().replaceAll("[\\s./]",""));
        course.setActive(true);
        return courseRepo.save(course);
    }
}