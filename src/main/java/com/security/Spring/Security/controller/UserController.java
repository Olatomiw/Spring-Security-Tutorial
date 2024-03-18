package com.security.Spring.Security.controller;

import com.security.Spring.Security.Service.CurrentUserService;
import com.security.Spring.Security.Util.ProfileOutput;
import com.security.Spring.Security.config.UserRole;
import com.security.Spring.Security.dto.UserDto;
import com.security.Spring.Security.model.MyUser;
import com.security.Spring.Security.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserService currentUserService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        MyUser user = new MyUser();
        Optional<MyUser> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userDto.getRole().equalsIgnoreCase("User")){
            user.setRole(UserRole.ROLE_USER);
        } else if (userDto.getRole().equalsIgnoreCase("Admin")) {
            user.setRole(UserRole.ROLE_ADMIN);
        } else if (userDto.getRole().equalsIgnoreCase("Doctor")) {
            user.setRole(UserRole.ROLE_DOCTOR);
        } else if (userDto.getRole().equalsIgnoreCase("Nurse")) {
            user.setRole(UserRole.ROLE_NURSE);
        }
        userRepository.save(user);
        return new ResponseEntity<>("Okay", HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<MyUser> optionalMyUser = userRepository.findById(id);
        ProfileOutput profileOutput = new ProfileOutput();
        try {
            if (optionalMyUser.isEmpty()){
                return new ResponseEntity<>("No User found", HttpStatus.BAD_REQUEST);
            }
            MyUser user = optionalMyUser.get();
            profileOutput.setName(user.getUsername());
            profileOutput.setRole(user.getRole().toString());
            return new ResponseEntity<>(profileOutput, HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    Getting the user that's currently logged in controller class
    @GetMapping("/currentUser")
    public ResponseEntity<?> loggedIn (){
     return currentUserService.getLoggedInUser();
    }

//    @PostMapping("/update_role")
//    public ResponseEntity<?> updateProfile(){
//        if (currentUserService.getLoggedInUser())
//        return null;
//    }
}
