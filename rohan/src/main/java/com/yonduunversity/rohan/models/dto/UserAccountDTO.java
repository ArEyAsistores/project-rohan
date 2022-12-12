package com.yonduunversity.rohan.models.dto;
import lombok.Data;
@Data
public class UserAccountDTO {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String role;

    public UserAccountDTO(String email, String firstname,String lastname,String role){
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }
}

