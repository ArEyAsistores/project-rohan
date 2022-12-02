package com.yonduunversity.rohan.controllers;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api") //Root path
public class UserController {
    private final UserService userService;

     //////////////////////////////
    ///GET: RETRIEVE ALL USER ///
    ////////////////////////////
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    ///////////////////////////
    ///POST: ADD NEW USER ///
    /////////////////////////
    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        URI uri = URI
                    .create(ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("api/user/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    ///////////////////////////
    ///POST: ADD NEW ROLE ///
    /////////////////////////
    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        URI uri = URI
                .create(ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/role/add").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    /////////////////////////////////
    ///POST: ASSIGN ROLE TO USER ///
    //////////////////////////////

    @PostMapping("/role/assign")
    public ResponseEntity<?> assignRole(@RequestBody RoleUser form){
        userService.assignRole(form.getEmail(),form.getRoleName());
        return ResponseEntity.ok().build();
    }


}
