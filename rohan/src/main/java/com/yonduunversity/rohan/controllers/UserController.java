package com.yonduunversity.rohan.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.services.ExerciseService;
import com.yonduunversity.rohan.services.GradeService;
import com.yonduunversity.rohan.services.QuizService;
import com.yonduunversity.rohan.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/users/{email}")
    public UserDTO getUser(@PathVariable String email) {
        return new UserDTO(userService.getUser(email));
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> getAllUser(@Param("keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {// With Pagination dapat
        List<UserDTO> listOfUser = userService.getUserByKeyword(keyword, pageNumber, pageSize);
        Pager pager = new Pager(listOfUser, pageNumber, pageSize);
        return ResponseEntity.ok().body(pager);
    }

    @GetMapping("/users")
    public Pager getAllUser(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

        List<UserDTO> userDTOS = userService.getUsers(pageNumber, pageSize).stream().map(UserDTO::new)
                .collect(Collectors.toList());
        Pager pager = new Pager(userDTOS, pageNumber, pageSize);
        return pager;
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody UserAccountDTO user, HttpServletRequest request) throws Exception {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/user/add").toUriString());

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String whoAddedToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(whoAddedToken);
        String whoAdded = decodedJWT.getSubject();

        return ResponseEntity.created(uri).body(userService.saveUser(user, whoAdded));
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/role/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/courses/add").toUriString());
        return ResponseEntity.created(uri).body(userService.addCourse(course));
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(@RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<CourseDTO> courseDTO = userService.getCourses(pageNumber, pageSize).stream().map(CourseDTO::new).toList();
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
        List<CourseDTO> listOfCourses = userService.getCourseByKeyword(keyword, pageNumber, pageSize);
        Pager pager = new Pager(listOfCourses, pageNumber, pageSize);
        return ResponseEntity.ok().body(pager);
    }
    @GetMapping("/students")
    public ResponseEntity<?> getStudentsByKeyword(@Param("keyword") String keyword,@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<StudentDTO> studentDTOS = userService.getStudentsByKeyword(keyword,pageNumber,pageSize);
        Pager pager = new Pager(studentDTOS, pageNumber, pageSize);
        return ResponseEntity.ok().body(pager);
    }
    @GetMapping("/students/search")
    public ResponseEntity<?> saerchStudentsByKeyword(@Param("keyword") String keyword,@RequestParam(name = "page", defaultValue = "0") int pageNumber,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        List<StudentDTO> studentDTOS = userService.getStudentsByKeyword(keyword,pageNumber,pageSize);
        Pager pager = new Pager(studentDTOS, pageNumber, pageSize);
        return ResponseEntity.ok().body(pager);
    }
    @GetMapping("/students/{email}")
    public ResponseEntity<?> getStudent(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getStudent(email));
    }

    @GetMapping("/courses/{code}")

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

    @PutMapping("/users/deactivate")
    public UserDTO deactivateUser(@RequestParam(name = "email", defaultValue = "") String email) {
        return new UserDTO(userService.deactivateUser(email));
    }

    @PutMapping("/courses/deactivate")
    public Course deactivateCourse(@RequestParam(name = "courseCode", defaultValue = "") String code) {
        return userService.deactivateCourse(code);
    }

    // Quiz
    @GetMapping("/quiz/getOk")
    public ResponseEntity getOK() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/quiz/addById")
    public ResponseEntity<Quiz> addQuizById(@RequestBody Quiz quiz, @Param("id") long id) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/quiz/addById").toUriString());
        return ResponseEntity.created(uri).body(quizService.addQuizById(quiz, id));
    }

    @PostMapping("/quiz/add")
    public ResponseEntity<QuizDTO> addQuiz(@RequestBody Quiz quiz, @Param("code") String code,
            @Param("batch") long batch) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/quiz/add").toUriString());
        return ResponseEntity.created(uri).body(new QuizDTO(quizService.addQuiz(quiz, code, batch)));
    }

    @PostMapping("/quiz/remove")
    public ResponseEntity<QuizDTO> removeQuiz(@Param("id") int id) {
        return new ResponseEntity<QuizDTO>(new QuizDTO(quizService.removeQuiz(id)), HttpStatus.OK);
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

    @PostMapping("/exercise/add")
    public ResponseEntity<ExerciseDTO> addExercise(@RequestBody Exercise exercise, @Param("code") String code,
            @Param("batch") long batch) {
        URI uri = URI
                .create(ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("api/exercise/add").toUriString());
        return ResponseEntity.created(uri).body(new ExerciseDTO(exerciseService.addExercise(exercise, code, batch)));
    }

    @PostMapping("/exercise/remove")
    public ResponseEntity<ExerciseDTO> removeExercise(@Param("id") int id) {

        return new ResponseEntity<ExerciseDTO>(new ExerciseDTO(exerciseService.removeExercise(id)), HttpStatus.OK);
    }

    // Grade
    @PostMapping("/grade/giveQuizScore")
    public ResponseEntity<GradeDTO> giveQuizScore(@Param("id") int id, @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<GradeDTO>(new GradeDTO(gradeService.giveQuizScore(id, email, score)), HttpStatus.OK);
    }

    @PostMapping("/grade/giveExerciseScore")
    public ResponseEntity<GradeDTO> giveExerciseScore(@Param("id") int id, @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<GradeDTO>(new GradeDTO(gradeService.giveExerciseScore(id, email, score)),
                HttpStatus.OK);
    }

    @PostMapping("/grade/giveProjectScore")
    public ResponseEntity<GradeDTO> giveProjectScore(@Param("code") String code, @Param("batch") long batch,
            @Param("email") String email,
            @Param("score") int score) {
        return new ResponseEntity<GradeDTO>(new GradeDTO(gradeService.giveProjectScore(code, batch, email, score)),
                HttpStatus.OK);
    }

    @GetMapping("/grade/retrieveStudentGrades")
    public ResponseEntity<GradeSheet> retrieveStudentGrades(@Param("email") String email,
            @Param("code") String code,
            @Param("batch") long batch) {

        return new ResponseEntity<GradeSheet>(
                new GradeSheet(gradeService.retrieveStudentGrades(email, code, batch), email, code, batch),
                HttpStatus.OK);
    }

    @GetMapping("/grade/retrieveClassGrades")
    public ResponseEntity<List<GradeDTO>> retrieveClassGrades(@Param("code") String code, @Param("batch") long batch) {
        return new ResponseEntity<List<GradeDTO>>(
                gradeService.retrieveClassGrades(code, batch).stream().map(GradeDTO::new).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/courses/{code}/classes")
    public CourseClassDTO getAllCoursesClasses(@PathVariable String code) {
        return new CourseClassDTO(userService.getCourse(code));
    }

}
