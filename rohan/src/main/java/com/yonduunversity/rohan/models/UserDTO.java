package com.yonduunversity.rohan.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private boolean isActive;
    private String role;
    public UserDTO (User user){
        this.setRole(user.getRoles().get(0).getName());
        this.setEmail(user.getEmail());
        this.setId(user.getId());
        this.setActive(user.isActive());
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
    }

}

