package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
