package com.yonduuniversity.rohan.services;

import java.util.List;

import com.yonduuniversity.rohan.models.Role;
import com.yonduuniversity.rohan.models.User;
import com.yonduuniversity.rohan.models.student.Student;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void assignRole(String email, String roleName);

    User getUser(String email);

    void addStudent(User user, Student student);

    List<User> getUsers();// Default getAllUser

    List<User> getUsers(String email, int pageNumber, int pageSize);// With Pagination

}
