package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import java.util.List;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;


import java.util.List;
import java.util.Map;
public interface UserService {

    Map<String, Object> saveUser(User user, String roleName);

    User saveUser(User user);
    ClassBatch saveClassBatch(ClassBatch classBatch);
    Role saveRole(Role role);
    void assignRole(String email, String roleName);

    void assignCourse(String email, long roleName);
    Course addCourse(Course course);
    void saveUser(Student student);
    List<User>getUsers();//Default getAllUser
    List<User>getUsers(int pageNumber, int pageSize);//With Pagination
    List<Course>getCourses(int pageNumber, int pageSize);//With Pagination
    List<Course>getCourseByKeyword(String keyword);
    User getUser(String email);
    Course getCourse(long courseCode);
    List<User>getUserByKeyword(String keyword);
    User deactivateUser(String email);

    Course deactivateCourse(long courseCode);
    void addStudent(User user, Student student);
}
