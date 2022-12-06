package com.yonduunversity.rohan.services;

import java.util.List;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;


import java.util.List;
import java.util.Map;
public interface UserService {

    Map<String, Object> saveUser(User user, String roleName);

    User saveUser(User user);

    Role saveRole(Role role);
    void assignRole(String email, String roleName);

    void saveUser(Student student);
    List<User>getUsers();//Default getAllUser
    List<User>getUsers(int pageNumber, int pageSize);//With Pagination
    User getUser(String email);
    List<User>getUserByKeyword(String keyword);
    User deactivateUser(String email);
    void addStudent(User user, Student student);
    List<User> getUsers(String email, int pageNumber, int pageSize);// With Pagination


}
