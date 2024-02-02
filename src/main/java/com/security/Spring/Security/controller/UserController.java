package com.security.Spring.Security.controller;

import com.security.Spring.Security.config.UserRole;
import com.security.Spring.Security.dto.UserDto;
import com.security.Spring.Security.model.MyUser;
import com.security.Spring.Security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        MyUser user = new MyUser();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("Okay", HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<MyUser> optionalMyUser = userRepository.findById(id);
        try {
            if (optionalMyUser.isEmpty()){
                return new ResponseEntity<>("No User found", HttpStatus.BAD_REQUEST);
            }
            MyUser user = optionalMyUser.get();
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
