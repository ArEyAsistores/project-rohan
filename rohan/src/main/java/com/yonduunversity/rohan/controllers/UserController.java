package com.yonduunversity.rohan.controllers;

import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.services.ExerciseService;
import com.yonduunversity.rohan.services.GradeService;
import com.yonduunversity.rohan.services.QuizService;
import com.yonduunversity.rohan.services.UserService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // Root path
public class UserController {
    @Autowired
    private ModelMapper modelMapper;
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
    public Pager getAllUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {


        List<UserDTO> userDTOS = userService.getUsers(pageNumber, pageSize).stream().map(UserDTO::new).collect(Collectors.toList());
        Pager pager = new Pager(userDTOS,pageNumber,pageSize);
        return pager ;
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
    public ResponseEntity<?> getAllCourses(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<CourseDTO> courseDTO = userService.getCourses(pageNumber, pageSize).stream().map(CourseDTO::new).toList();
        Pager pager = new Pager(courseDTO, pageNumber,pageSize);
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/courses").toUriString());
        return ResponseEntity.created(uri).body(pager);
    }

    @GetMapping("/courses/search")
    public ResponseEntity<List<Course>> getCourseByKeyword(@Param("keyword") String keyword) {
        List<Course> listOfUser = userService.getCourseByKeyword(keyword);
        return ResponseEntity.ok().body(listOfUser);
    }

    @GetMapping("/course/{code}")

    public Course getCourse(@PathVariable String code) {
        return userService.getCourse(code);
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/assign")
    public ResponseEntity<?> assignRole(@RequestBody RoleUser form) {
        userService.assignRole(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/deactivate")
    public User deactivateUser(@RequestParam(name = "email", defaultValue = "") String email) {
        return userService.deactivateUser(email);
    }

    @PutMapping("/course/deactivate")
    public Course deactivateCourse(@RequestParam(name = "courseCode", defaultValue = "") String code) {
        return userService.deactivateCourse(code);
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
    public ResponseEntity removeQuiz(@Param("id") int id) {
        quizService.removeQuiz(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
    public ResponseEntity removeExercise(@Param("id") int id) {
        exerciseService.removeExercise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Grade
    @GetMapping("/grade/giveQuizScore")
    public ResponseEntity<Grade> giveQuizScore(@Param("id") int id, @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<Grade>(gradeService.giveQuizScore(id, email, score), HttpStatus.OK);
    }

    @GetMapping("/grade/giveExerciseScore")
    public ResponseEntity<Grade> giveExerciseScore(@Param("id") int id, @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<Grade>(gradeService.giveExerciseScore(id, email, score), HttpStatus.OK);
    }

    @GetMapping("/grade/giveProjectScore")
    public ResponseEntity<Grade> giveProjectScore(@Param("id") int id, @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<Grade>(gradeService.giveProjectScore(id, email, score), HttpStatus.OK);
    }

    @GetMapping("/grade/retrieveStudentGrades")
    public ResponseEntity<List<Grade>> retrieveStudentGrades(@Param("email") String email) {
        return new ResponseEntity<List<Grade>>(gradeService.retrieveStudentGrades(email),
                HttpStatus.OK);
    }

    @GetMapping("/grade/retrieveClassGrades")
    public ResponseEntity<List<Grade>> retrieveClassGrades(@Param("id") long id) {
        return new ResponseEntity<List<Grade>>(gradeService.retrieveClassGrades(id),
                HttpStatus.OK);
    }

    @GetMapping("/user/courses/{code}/classes")
    public CourseClassDTO getAllCoursesClasses(@PathVariable String code){
        return new CourseClassDTO(userService.getCourse(code));
    }

}
