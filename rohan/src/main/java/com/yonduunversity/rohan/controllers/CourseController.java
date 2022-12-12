package com.yonduunversity.rohan.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.dto.*;
import com.yonduunversity.rohan.services.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // Root path
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;
    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/courses/add").toUriString());
        return ResponseEntity.created(uri).body(courseService.addCourse(course));
    }
    @GetMapping("/courses/{code}")
    public Course getCourse(@PathVariable String code) {
        return courseService.getCourse(code);
    }
    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<CourseDTO> courseDTO = courseService.getCourses(pageNumber, pageSize).stream().map(CourseDTO::new).toList();
        Pager pager = new Pager(courseDTO, pageNumber, pageSize);
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/courses").toUriString());
        return ResponseEntity.created(uri).body(pager);
    }
    @GetMapping("/courses/search")
    public ResponseEntity<?> getCourseByKeyword(@Param("keyword") String keyword,
                                                @RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<CourseDTO> listOfCourses = courseService.getCourseByKeyword(keyword, pageNumber, pageSize);
        Pager pager = new Pager(listOfCourses, pageNumber, pageSize);
        return ResponseEntity.ok().body(pager);
    }

    @PutMapping("/courses/deactivate")
    public Course deactivateCourse(@RequestParam(name = "courseCode", defaultValue = "") String code) {
        return courseService.deactivateCourse(code);
    }
    @GetMapping("/courses/{code}/classes")
    public CourseClassDTO getAllCoursesClasses(@PathVariable String code) {
        return new CourseClassDTO(courseService.getCourse(code));
    }
}
