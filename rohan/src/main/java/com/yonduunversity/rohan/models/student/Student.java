package com.yonduunversity.rohan.models.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yonduunversity.rohan.models.Grade;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.*;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student extends User {
    // *NOTE TO-do FK(BatchID and CourseID) AND CODE CLEAN-UP
    private boolean isClass;
    @ManyToMany
    @JsonIgnore
    private Collection<ClassBatch> classBatches = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public Student(User user) {
        super(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPassword(),
                user.isActive(), user.getRoles());
    }
}
