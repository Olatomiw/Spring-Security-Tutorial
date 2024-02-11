package com.security.Spring.Security.repository;

import com.security.Spring.Security.config.UserDetail;
import com.security.Spring.Security.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional <MyUser> foundUser = userRepository.findByUsername(username);

           return foundUser.map(user ->
                   new User(user.getUsername(), user.getPassword(),
                           List.of(new SimpleGrantedAuthority(user.getRole().toString()))))
                   .orElseThrow(()-> new UsernameNotFoundException("username not found" + username));

    }
}
