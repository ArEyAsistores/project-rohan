package com.yonduuniversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduuniversity.rohan.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
