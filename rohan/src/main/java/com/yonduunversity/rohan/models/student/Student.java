package com.yonduunversity.rohan.models.student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yonduunversity.rohan.models.*;
import jakarta.persistence.*;

import com.yonduunversity.rohan.models.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student extends User
{
    //*NOTE TO-do FK(BatchID and CourseID) AND CODE CLEAN-UP
    private boolean isClass;
    @ManyToMany
    @JsonIgnore
    private Collection<ClassBatch> classBatches = new ArrayList<>();

    public Student(User user) {
        super(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPassword(), user.isActive(), user.getRoles());
    }
}
