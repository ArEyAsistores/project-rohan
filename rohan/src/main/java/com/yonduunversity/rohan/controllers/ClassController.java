package com.yonduunversity.rohan.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.dto.ClassCourseDTO;
import com.yonduunversity.rohan.models.dto.ClassStudentsDTO;
import com.yonduunversity.rohan.services.ClassService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // Root path
public class ClassController {
    private final ClassService classService;

    @PostMapping("/user/courses/{code}/classes/{batch}/enroll")
    public ResponseEntity<?> enrollStudent (@RequestBody Map<String, Object> email, @PathVariable String code, @PathVariable long batch) throws Exception {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class/add").toUriString());

       return ResponseEntity.created(uri).body(new ClassStudentsDTO(classService.enrollStudent(email.get("email").toString(),code, batch)));
    }
    @PutMapping("/user/courses/{code}/classes/{batch}/unenroll")
    public ResponseEntity<?> unrollStudent (@RequestBody Map<String, Object> email, @PathVariable String code, @PathVariable long batch, HttpServletRequest request, HttpServletResponse response) throws Exception {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class/add").toUriString());

        return ResponseEntity.created(uri).body(new ClassCourseDTO(classService.unEnrollStudent(email.get("email").toString(),code, batch)));
    }
    @GetMapping("/classes/student")
    public ResponseEntity<?> findStudentCourse (@RequestParam(name = "email") String email ){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class/add").toUriString());
        return ResponseEntity.created(uri).body(classService.findStudentClass(email).stream().map(ClassCourseDTO::new).collect(Collectors.toList()));
    }
    @PutMapping("/user/courses/{code}/classes/{batch}/deactivate")
    public ResponseEntity<?> deactivateClass (@PathVariable String code, @PathVariable long batch) throws Exception {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("").toUriString());
        return ResponseEntity.created(uri).body(new ClassCourseDTO(classService.deactivateClass(code, batch)));
    }
    @PostMapping("/user/add/class")
    public ResponseEntity<?> saveClass(@RequestBody ClassBatch classBatch, HttpServletRequest request, HttpServletResponse response) throws Exception {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class").toUriString());

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String whoAddedToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(whoAddedToken);
        String whoAdded = decodedJWT.getSubject();
        return ResponseEntity.created(uri).body(new ClassCourseDTO(classService.saveClass(classBatch,whoAdded)));
    }
    @GetMapping("/user/courses/{code}/classes/{batch}/students")
    public ResponseEntity<?>  getClass(@PathVariable String code, @PathVariable Long batch){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("").toUriString());

        return ResponseEntity.created(uri).body(new ClassStudentsDTO(classService.getClassStudents(code,batch)));
    }

    @GetMapping("/classes")
    public ResponseEntity<?> getAllCourses(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<ClassCourseDTO> classCourseDTOS = classService.getAllClassBatch(pageNumber, pageSize).stream().map(ClassCourseDTO::new).toList();
        Pager pager = new Pager(classCourseDTOS, pageNumber, pageSize);
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/classes").toUriString());
        return ResponseEntity.created(uri).body(pager);
    }

    @GetMapping("/classes/search")
    public ResponseEntity<?> getCourseByKeyword(@Param("keyword") String keyword, @RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<ClassCourseDTO> classCourseDTOS = classService.getClassByKeyword(keyword,pageNumber,pageSize);
        Pager pager = new Pager(classCourseDTOS, pageNumber, pageSize);
        return ResponseEntity.ok().body(pager);
    }

}


