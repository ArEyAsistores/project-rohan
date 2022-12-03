package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);

    void assignRole(String email, String roleName);
    User getUser(String email);
    void addStudent(User user, Student student);
    List<User>getUsers();//Default getAllUser
    List<User>getUsers(String email, int pageNumber, int pageSize);//With Pagination


}
