package com.yonduunversity.rohan.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.services.ClassService;
import com.yonduunversity.rohan.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ModelMapper modelMapper;
    private final ClassService classService;

    @PostMapping("/user/courses/{code}/classes/{batch}/enroll")
    public ResponseEntity<?> enrollStudent (@RequestBody Map<String, Object> email, @PathVariable String code, @PathVariable long batch){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class/add").toUriString());

       return ResponseEntity.created(uri).body(classService.enrollStudent(email.get("email").toString(),code, batch));
    }
    @PostMapping("/user/courses/{code}/classes/{batch}/unenroll")
    public ResponseEntity<?> unrollStudent (@RequestBody Map<String, Object> email, @PathVariable String code, @PathVariable long batch, HttpServletRequest request, HttpServletResponse response){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class/add").toUriString());

        return ResponseEntity.created(uri).body(classService.unEnrollStudent(email.get("email").toString(),code, batch));
    }
    @GetMapping("/user/courses/sample")
    public ResponseEntity<?> findStudentCourse (){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/class/add").toUriString());
        return ResponseEntity.created(uri).body(classService.findStudentCourse().stream().map(post -> modelMapper.map(post, ClassBatchDTO.class)).collect(Collectors.toList()));
    }
    @PostMapping("/user/courses/{code}/classes/{batch}/deactivate")
    public ResponseEntity<?> deactivateClass (@PathVariable String code, @PathVariable long batch){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("").toUriString());
        return ResponseEntity.created(uri).body(classService.deactivateClass(code, batch));
    }
    @PostMapping("/user/add/class")
    public ResponseEntity<?> saveClass(@RequestBody ClassBatch classBatch, HttpServletRequest request, HttpServletResponse response) {
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
        return ResponseEntity.created(uri).body(classService.saveClass(classBatch,whoAdded));
    }
    @GetMapping("/user/classes")
    public List<ClassBatchDTO> getAllClassBatch(){
            return classService.getAllClassBatch().stream().map(post -> modelMapper.map(post, ClassBatchDTO.class)).collect(Collectors.toList());
    }
}


