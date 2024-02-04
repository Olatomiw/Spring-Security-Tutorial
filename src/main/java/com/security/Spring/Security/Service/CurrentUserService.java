package com.security.Spring.Security.Service;

import org.springframework.http.ResponseEntity;

public interface CurrentUserService {

    public ResponseEntity<?> getLoggedInUser();
}
