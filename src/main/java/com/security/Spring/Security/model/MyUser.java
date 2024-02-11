package com.security.Spring.Security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.Spring.Security.config.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @Column(nullable = false)
    private String username;
   @Column(nullable = false)
    private String password;
//   @Column(nullable = false)
//   @ManyToMany(fetch = FetchType.LAZY)
//   @JoinTable(name = "User_Roles",
//   joinColumns = @JoinColumn(name = "myuser_id"),
//   inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> role = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
