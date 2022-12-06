package com.yonduuniversity.rohan.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String firstname;
    private String lastname;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER) // load Database From Role when this user RUN
    private Collection<Role> roles = new ArrayList<>();

}
