package com.yonduunversity.rohan.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yonduunversity.rohan.exception.ProjectNotFoundException;
import com.yonduunversity.rohan.models.Project;
import com.yonduunversity.rohan.services.ProjectService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    static Project unwrapProject(Optional<Project> entity, long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new ProjectNotFoundException(id);
    }

}
