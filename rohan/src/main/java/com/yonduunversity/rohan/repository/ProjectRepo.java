package com.yonduunversity.rohan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonduunversity.rohan.models.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
