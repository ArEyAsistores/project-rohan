package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;

import java.util.HashMap;
import java.util.List;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;


import java.util.Map;
public interface UserService {

    Map<String, Object> saveUser(User user, String roleName);

    User saveUser(User user);
    ClassBatch enrollStudent(String email, String code,long batchNumber);
    ClassBatch unEnrollStudent(String email,  String code, long batchNumber);
    ClassBatch deactivateClass(String code, long batchNumber);
    List<ClassBatch> findStudentCourse();
    Role saveRole(Role role);
    void assignRole(String email, String roleName);

    void assignCourse(String email, long roleName);
    Course addCourse(Course course);
    void saveUser(Student student);
    List<User>getUsers();//Default getAllUser
    List<ClassBatch>getAllClassBatch();
    List<User>getUsers(int pageNumber, int pageSize);//With Pagination
    List<Course> getCourses(int pageNumber, int pageSize);//With Pagination
    List<Course>getCourseByKeyword(String keyword);
    User getUser(String email);
    Course getCourse(String code);
    List<User>getUserByKeyword(String keyword);
    User deactivateUser(String email);
    Course deactivateCourse(String code);
    void addStudent(User user, Student student);

}
