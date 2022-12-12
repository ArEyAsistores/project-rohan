package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.exception.EmailNotFoundException;
import com.yonduunversity.rohan.exception.UnauthenticatedAccessException;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.*;
import com.yonduunversity.rohan.repository.pagination.CourseRepoPaginate;
import com.yonduunversity.rohan.repository.pagination.UserRepoPaginate;
import com.yonduunversity.rohan.services.EmailSenderService;
import com.yonduunversity.rohan.services.UserService;
import com.yonduunversity.rohan.util.PasswordGen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepoPaginate userRepoPagingate;
    private final CourseRepoPaginate courseRepoPaginate;
    private final CourseRepo courseRepo;
    private final EmailSenderService emailSenderService;
    private final ClassBatchRepo classBatchRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            log.info("Email not found");
            throw new EmailNotFoundException();

        } else {
            log.info("Email found {}:", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public UserAccountDTO saveUser(UserAccountDTO userPasswordDTO, String whoAdded) {
        Role role = roleRepo.findByName(userPasswordDTO.getRole());
        User userAdded = userRepo.findByEmail(whoAdded);
        String userPass = PasswordGen.generateUserPassaword(12);
        if (userAdded.getRoles().get(0).getName().equalsIgnoreCase("ADMIN")) {
            User user = new User();
            user.setEmail(userPasswordDTO.getEmail());
            user.setFirstname(userPasswordDTO.getFirstname());
            user.setLastname(userPasswordDTO.getLastname());
            user.setActive(true);
            user.getRoles().add(role);
            userPasswordDTO.setPassword(userPass);
            log.info(userPasswordDTO.getEmail() + " the password id: " + userPass);
            user.setPassword(passwordEncoder.encode(userPasswordDTO.getPassword()));

            if (userPasswordDTO.getRole().equalsIgnoreCase("STUDENT")) {
                saveUser(new Student(user));
            } else {
                userRepo.save(user);
            }

            String message = """
                    Welcome to Project Rohan\n
                    Here is your Project Rohan Account
                    Email Address: %s
                    Password: %s
                    Role: %s
                    """.formatted(userPasswordDTO.getEmail(), userPasswordDTO.getPassword(), userPasswordDTO.getRole());

            emailSenderService.sendEmail(userPasswordDTO.getEmail(),
                    "Project Rohan Account - " + userPasswordDTO.getLastname() + ", " + userPasswordDTO.getFirstname(),
                    message);

        } else {
            throw new UnauthenticatedAccessException(userAdded.getEmail());
        }

        return userPasswordDTO;
    }

    @Override
    public UserAccountDTO defaultUsers(UserAccountDTO userPasswordDTO) {
        Role role = roleRepo.findByName(userPasswordDTO.getRole());
        String userPass = PasswordGen.generateUserPassaword(12);
        User user = new User();
        user.setEmail(userPasswordDTO.getEmail());
        user.setFirstname(userPasswordDTO.getFirstname());
        user.setLastname(userPasswordDTO.getLastname());
        user.setActive(true);
        user.getRoles().add(role);
        userPasswordDTO.setPassword(userPass);
        log.info(userPasswordDTO.getEmail() + " the password id: " + userPass);
        user.setPassword(passwordEncoder.encode(userPasswordDTO.getPassword()));
        if (userPasswordDTO.getRole().equalsIgnoreCase("STUDENT")) {
            saveUser(new Student(user));
        } else {
            userRepo.save(user);
        }
        return userPasswordDTO;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void saveUser(Student student) {
        student.setClass(false);
        log.info("{} added to Database", student.getId());
        studentRepo.save(student);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("{} added to Database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void assignRole(String email, String roleName) {
        User user = userRepo.findByEmail(email);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        log.info("Adding Role to {} to user {} ", roleName, email);
    }

    @Override
    public ClassBatch enrollStudent(String email, String code, long batchNumber) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
        Student studentEnrolled = studentRepo.findByEmail(email);
        studentEnrolled.setActive(true);
        classBatch.getStudents().add(studentEnrolled);
        studentEnrolled.getClassBatches().add(classBatch);
        return classBatchRepo.save(classBatch);
    }

    @Override
    public ClassBatch unEnrollStudent(String email, String code, long batchNumber) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
        Student studentUnEnroll = studentRepo.findByEmail(email);
        classBatch.getStudents().remove(studentUnEnroll);
        classBatch.setActive(false);
        return classBatchRepo.save(classBatch);
    }

    @Override
    public List<ClassBatch> findStudentCourse() {
        Student studentUnEnroll = studentRepo.findByEmail("student2.rey@yahoo.com");
        return studentUnEnroll.getClassBatches().stream().toList();
    }

    @Override
    public ClassBatch deactivateClass(String code, long batchNumber) {
        ClassBatch classBatch = classBatchRepo.findClassBatchByCourseCodeAndId(code, batchNumber);
        classBatch.getStudents().removeAll(classBatch.getStudents());
        classBatch.setActive(false);
        return classBatchRepo.save(classBatch);
    }

    @Override
    public Course addCourse(Course course) {
        log.info("{} added to Database", course.getTitle());
        log.info("{} added to Database", course.getCode());
        course.setActive(true);
        return courseRepo.save(course);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users {} ");
        return userRepo.findAll();
    }

    @Override
    public List<User> getUsers(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<User> pagedResult = userRepoPagingate.findAll(paging);
        return pagedResult.stream().toList();
    }

    @Override
    public List<Course> getCourses(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<Course> pagedResult = courseRepoPaginate.findAll(paging);
        return pagedResult.stream().toList();
    }

    @Override
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Course getCourse(String code) {
        return courseRepo.findCourseByCode(code);
    }

    @Override
    public List<User> getUserByKeyword(String keyword) {
        if (keyword != null) {
            return userRepo.findAllByKeyword(keyword);
        } else {
            return userRepo.findAll();
        }
    }

    @Override
    public List<Course> getCourseByKeyword(String keyword) {
        if (keyword != null) {
            return courseRepo.findAllByKeyword(keyword);
        } else {
            return courseRepo.findAll();
        }
    }

    @Override
    public User deactivateUser(String email) {
        User user = userRepo.findByEmail(email);
        if (user.isActive()) {
            user.setActive(false);
        }
        return user;
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
    public List<ClassBatch> getAllClassBatch() {
        return classBatchRepo.findAll();
    }
}
