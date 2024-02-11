package com.security.Spring.Security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//@Entity
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Roles;
//    @ManyToMany(mappedBy = "role")
    @JsonIgnore
    private Set<MyUser>myUserSet = new HashSet<>();
}
