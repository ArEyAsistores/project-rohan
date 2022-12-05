package com.yonduunversity.rohan.repository;

import com.yonduunversity.rohan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User c WHERE c.email = :email")
    boolean emailEquals(@Param("email") String email);
    @Query("SELECT p FROM User p WHERE p.email LIKE %?1%"
            + " OR p.firstname LIKE %?1%"
            + " OR p.lastname LIKE %?1%")
    List<User> findAllByKeyword(String keyword);
}
