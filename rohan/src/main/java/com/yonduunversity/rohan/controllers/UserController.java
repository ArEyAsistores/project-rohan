package com.yonduunversity.rohan.controllers;

import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Exercise;
import com.yonduunversity.rohan.models.Grade;
import com.yonduunversity.rohan.models.Quiz;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.services.ExerciseService;
import com.yonduunversity.rohan.services.GradeService;
import com.yonduunversity.rohan.services.QuizService;
import com.yonduunversity.rohan.services.UserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.services.UserService;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // Root path
public class UserController {
    private final UserService userService;
    private final QuizService quizService;
    private final ExerciseService exerciseService;
    private final GradeService gradeService;

    //////////////////////////////
    /// GET: RETRIEVE ALL USER ///
    ////////////////////////////

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
    public List<User> getAllUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getUsers(pageNumber, pageSize);
    }

    ///////////////////////////
    /// POST: ADD NEW USER ///
    /////////////////////////
    @PostMapping("/user/add/{roleName}")
    public ResponseEntity<?> addUser(@RequestBody User user, @PathVariable String roleName) {

        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/add").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(user, roleName));
    }

    ///////////////////////////
    /// POST: ADD NEW ROLE ///
    /////////////////////////

    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/role/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/course/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/course/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addCourse(course));
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return userService.getCourses(pageNumber, pageSize);
    }

    @GetMapping("/courses/search")
    public ResponseEntity<List<Course>> getCourseByKeyword(@Param("keyword") String keyword) {
        List<Course> listOfUser = userService.getCourseByKeyword(keyword);
        return ResponseEntity.ok().body(listOfUser);
    }

    @GetMapping("/course")
    public Course getCourse(@Param("courseCode") long courseCode) {
        return userService.getCourse(courseCode);
    }
    /////////////////////////////////
    /// POST: ASSIGN ROLE TO USER ///
    //////////////////////////////

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    ///////////////////////////
    /// POST: ADD NEW ROLE ///
    /////////////////////////

    /////////////////////////////////
    /// POST: ASSIGN ROLE TO USER ///
    //////////////////////////////

    @PostMapping("/role/assign")
    public ResponseEntity<?> assignRole(@RequestBody RoleUser form) {
        userService.assignRole(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    /////////////////////////////////
    /// POST: ASSIGN ROLE TO USER ///
    //////////////////////////////
    @PutMapping("/user/deactivate")
    public User deactivateUser(@RequestParam(name = "email", defaultValue = "") String email) {
        return userService.deactivateUser(email);

    }

    /////////////////////////////////
    /// POST: ASSIGN ROLE TO USER ///
    //////////////////////////////
    @PutMapping("/course/deactivate")
    public Course deactivateCourse(@RequestParam(name = "courseCode", defaultValue = "") Long courseCode) {
        return userService.deactivateCourse(courseCode);
    }

    // Quiz
    @GetMapping("/quiz/getOk")
    public ResponseEntity getOK() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/quiz/addById")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz, @Param("id") long id) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/quiz/add").toUriString());
        return ResponseEntity.created(uri).body(quizService.addQuizById(quiz, id));
    }

    @GetMapping("/quiz/remove")
    public ResponseEntity removeQuiz(@PathVariable int id) {
        quizService.removeQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/grade/giveQuizScore")
    public ResponseEntity<Grade> giveQuizScore(@Param("id") int id, @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<Grade>(gradeService.giveQuizScore(id, email, score), HttpStatus.OK);
    }

    // Exercise
    @PostMapping("/exercise/addById")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise, @Param("id") long id) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/exercise/add").toUriString());
        return ResponseEntity.created(uri).body(exerciseService.addExerciseById(exercise, id));
    }

    @GetMapping("/exercise/remove")
    public ResponseEntity removeExercise(@PathVariable int id) {
        exerciseService.removeExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
