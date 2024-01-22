package com.security.Spring.Security.repository;

import com.security.Spring.Security.config.UserDetail;
import com.security.Spring.Security.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       MyUser foundUser = userRepository.findByUsername(username);

       if (foundUser == null){
           throw new UsernameNotFoundException("Username not found");
       }

       return new UserDetail(foundUser);
    }
}
