package com.yonduunversity.rohan.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)

    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER) // load Database From Role when this user RUN
    private List<Role> roles = new ArrayList<>();

}
