package com.yonduuniversity.rohan.models.student;

import com.yonduuniversity.rohan.models.User;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Student extends User {
    // *NOTE TO-do FK(BatchID and CourseID) AND CODE CLEAN-UP

    private boolean isClass;
    private boolean isActive;

}
