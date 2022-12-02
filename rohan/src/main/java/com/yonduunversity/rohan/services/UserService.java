package com.yonduunversity.rohan.services;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);

    void assignRole(String email, String roleName);
    User getUser(String email);
    List<User>getUsers();
}
