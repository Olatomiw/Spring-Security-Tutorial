package com.security.Spring.Security.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class UserDto {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;
}
