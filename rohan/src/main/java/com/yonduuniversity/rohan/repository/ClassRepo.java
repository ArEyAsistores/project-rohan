package com.yonduuniversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduuniversity.rohan.models.Class;

public interface ClassRepo extends JpaRepository<Class, Integer> {

}