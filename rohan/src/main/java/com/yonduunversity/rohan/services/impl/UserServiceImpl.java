package com.yonduunversity.rohan.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.RoleRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.repository.UserRepo;
import com.yonduunversity.rohan.repository.pagination.UserRepoPagingate;
import com.yonduunversity.rohan.services.UserService;

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
    private final UserRepoPagingate userRepoPagingate;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            log.info("Email not found");
            throw new UsernameNotFoundException("Email not found");

        } else {
            log.info("Email found {}:", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        log.info("{} added to Database", user.getId());
        return userRepo.save(user);
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
    public User getUser(String email) {
        log.info("Fetching user {} ", email);
        return userRepo.findByEmail(email);
    }

    @Override
    public void addStudent(User user, Student student) {
        // if(user.getRoles().contains("SME") || user.getRoles().contains("ADMIN")){
        // //*NOTE for RESTRICTION
        student.setEmail(user.getEmail());
        student.setPassword(passwordEncoder.encode(user.getPassword()));
        student.setPassword(student.getPassword());
        student.setFirstname(user.getFirstname());
        student.setLastname(user.getLastname());
        Role role = roleRepo.findByName("STUDENT");
        student.getRoles().add(role);
        student.setActive(true);
        student.setClass(false);
        // }
        log.info("{} added to Database", student.getId());
        studentRepo.save(student);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users {} ");
        return userRepo.findAll();
    }

    @Override
    public List<User> getUsers(String email, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        Page<User> pagedResult = userRepoPagingate.findAll(paging);
        return pagedResult.toList();
    }
}
