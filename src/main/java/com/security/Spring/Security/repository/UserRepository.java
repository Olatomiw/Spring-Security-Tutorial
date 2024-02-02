package com.security.Spring.Security.repository;

import com.security.Spring.Security.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {



    Optional <MyUser> findByUsername(String username);
}
