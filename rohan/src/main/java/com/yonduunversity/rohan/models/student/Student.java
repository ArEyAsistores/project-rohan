package com.yonduunversity.rohan.models.student;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User
{
    //*NOTE TO-do FK(BatchID and CourseID) AND CODE CLEAN-UP
    private boolean isClass;

    public Student(User user) {
        super(user.getId(), user.getEmail(), user.getFirstname(), user.getLastname(), user.getPassword(), user.isActive(), user.getRoles());
    }
}
