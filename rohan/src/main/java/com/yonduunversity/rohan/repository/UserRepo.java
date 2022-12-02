package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
