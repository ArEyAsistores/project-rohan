package com.yonduunversity.rohan.models;

import com.yonduunversity.rohan.models.student.Student;
import lombok.Data;

@Data
public class StudentDTO {


    private String email;
    private String firstname;
    private String lastname;
    private boolean isActive;


    public StudentDTO(Student student){
        this.email = student.getEmail();
        this.firstname = student.getFirstname();
        this.lastname = student.getLastname();
        this.isActive = student.isActive();
    }
}

