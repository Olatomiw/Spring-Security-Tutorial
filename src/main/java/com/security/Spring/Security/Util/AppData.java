package com.security.Spring.Security.Util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppData <G> {

    private String message;
    private G data;
}
