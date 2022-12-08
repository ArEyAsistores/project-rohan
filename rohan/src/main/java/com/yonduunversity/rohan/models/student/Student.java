package com.yonduunversity.rohan.models.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yonduunversity.rohan.models.Course;
import com.yonduunversity.rohan.models.Grade;
import com.yonduunversity.rohan.models.Quiz;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import jakarta.persistence.*;

import com.yonduunversity.rohan.models.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student extends User {
    // *NOTE TO-do FK(BatchID and CourseID) AND CODE CLEAN-UP
    private boolean isClass;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Course> course = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public Student(User user) {
        super(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPassword(),
                user.isActive(), user.getRoles());
    }
}
