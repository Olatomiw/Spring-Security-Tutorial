package com.security.Spring.Security.repository;

import com.security.Spring.Security.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
