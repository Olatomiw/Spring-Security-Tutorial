package com.security.Spring.Security.config;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum UserRole {
    ROLE_USER("user"),
    ROLE_ADMIN("Admin");


    String role;
//    String admin;
    UserRole(String role){
       this.role = role;
    }


}
