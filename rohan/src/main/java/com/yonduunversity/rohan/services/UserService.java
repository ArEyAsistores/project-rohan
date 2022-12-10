package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.*;

import java.util.List;

import com.yonduunversity.rohan.models.student.Student;

public interface UserService {


    UserAccountDTO saveUser(UserAccountDTO user, String whoAdded) throws Exception;
    UserAccountDTO defaultUsers(UserAccountDTO user) ;
    User saveUser(User user);
    ClassBatch enrollStudent(String email, String code,long batchNumber);
    ClassBatch unEnrollStudent(String email,  String code, long batchNumber);
    ClassBatch deactivateClass(String code, long batchNumber);
    List<ClassBatch> findStudentCourse();
    Role saveRole(Role role);
    void assignRole(String email, String roleName);
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

}
