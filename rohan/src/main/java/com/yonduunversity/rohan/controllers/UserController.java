package com.yonduunversity.rohan.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonView;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.student.Student;
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

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // Root path
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
    private final UserService userService;
    @GetMapping("/user")
    public User getUser(@Param("email") String email) {
        return userService.getUser(email);
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> getAllUser(@Param("keyword") String keyword) {
        List<User> listOfUser = userService.getUserByKeyword(keyword);
        return ResponseEntity.ok().body(listOfUser);
    }

    @GetMapping("/users")
    public List<User> getAllUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getUsers(pageNumber, pageSize);
    }
    @PostMapping("/user/add/{roleName}")
    public ResponseEntity<?> addUser(@RequestBody User user, @PathVariable String roleName) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/add").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(user, roleName));
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/role/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/course/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/course/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addCourse(course));
    }
    @GetMapping("/courses")
    public  ResponseEntity<?>  getAllCourses(@RequestParam(name = "page", defaultValue = "0") int pageNumber, @RequestParam(name = "pageSize", defaultValue = "10")   int pageSize){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/courses").toUriString());
        return ResponseEntity.created(uri).body(userService.getCourses(pageNumber,pageSize));
    }
    @GetMapping("/courses/search")
    public ResponseEntity<List<Course>> getCourseByKeyword(@Param("keyword") String keyword){
        List<Course> listOfUser = userService.getCourseByKeyword(keyword);
        return ResponseEntity.ok().body(listOfUser);
    }
    @GetMapping("/course/{code}")

    public Course getCourse(@PathVariable String code){
        return userService.getCourse(code);
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser (@RequestBody User user){
            URI uri = URI
                    .create(ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("api/user/add").toUriString());
            return ResponseEntity.created(uri).body(userService.saveUser(user));
        }

    @PostMapping("/role/assign")
    public ResponseEntity<?> assignRole (@RequestBody RoleUser form){
        userService.assignRole(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/deactivate")
    public User deactivateUser (@RequestParam(name = "email", defaultValue = "") String email){
        return userService.deactivateUser(email);
    }

    @PutMapping("/course/deactivate")
    public Course deactivateCourse(@RequestParam(name = "courseCode", defaultValue = "") String code){
        return userService.deactivateCourse(code);
    }
}


