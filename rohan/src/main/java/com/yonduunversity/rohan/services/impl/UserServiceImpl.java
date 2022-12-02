package com.yonduunversity.rohan.services.impl;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.repository.RoleRepo;
import com.yonduunversity.rohan.repository.UserRepo;
import com.yonduunversity.rohan.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService//, UserDetailsService *NOTE: FOR JWT
{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    //private final PasswordEncoder passwordEncoder; *NOTE: FOR JWT

//*NOTE: FOR JWT
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepo.findByEmail(email);
//        if(user == null){
//            log.info("Email not found");
//            throw new UsernameNotFoundException("Email not found");
//
//        }else{
//            log.info("Email found {}:", email);
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach( role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
//    }

    @Override
    public User saveUser(User user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));*NOTE: FOR JWT
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
        log.info("Adding Role to {} to user {} ",roleName,email);

    }

    @Override
    public User getUser(String email) {
        log.info("Fetching user {} ", email);
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users {} ");
        return userRepo.findAll();
    }


}
