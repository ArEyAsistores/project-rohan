package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.RoleRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.repository.UserRepo;
import com.yonduunversity.rohan.repository.pagination.UserRepoPagingate;
import com.yonduunversity.rohan.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepoPagingate userRepoPagingate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(user == null){
            log.info("Email not found");
            throw new UsernameNotFoundException("Email not found");

        }else{
            log.info("Email found {}:", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach( role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
    }

    @Override
    public Map<String,Object> saveUser(User user, String roleName) {
        Role role = roleRepo.findByName(roleName);
        Map<String, Object> message = new LinkedHashMap<>();
        if(!userRepo.emailEquals(user.getEmail())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPassword(user.getPassword());
            user.getRoles().add(role);
            user.setActive(true);

            if (roleName.equals("student")){
                 saveUser(new Student(user));
            }else{
                 userRepo.save(user);
            }
            message.put("user",user);
            message.put("message", user.getEmail() +" successfully created.");
            message.put("status",  String.valueOf(OK.value()));
        }else{
            message.put("message",  "This Email: " + user.getEmail() + " is taken.");
            message.put("status",  String.valueOf(FORBIDDEN.value()));
        }

    return message;
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
        log.info("Adding Role to {} to user {} ",roleName,email);

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
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUserByKeyword(String keyword) {
        if(keyword != null){
            return userRepo.findAllByKeyword(keyword);
        }else{
            return userRepo.findAll();
        }
    }
    @Override
    public User deactivateUser(String email) {
       User user =  userRepo.findByEmail(email);
       if(user.isActive()){
           user.setActive(false);
       }
       return user;
    }
}
