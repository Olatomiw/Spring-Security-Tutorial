package com.security.Spring.Security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class AuthController {

    @GetMapping("/welcome")
    public ResponseEntity<?> welcomeBack(){
        return new ResponseEntity<>("Welcome", HttpStatus.ACCEPTED);
    }
}
