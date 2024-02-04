package com.security.Spring.Security.Service.ServiceImpl;

import com.security.Spring.Security.Service.CurrentUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Service

public class CurrentUserServiceImpl implements CurrentUserService {
    @Override
    public ResponseEntity<?> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails){
                UserDetails username = (UserDetails) authentication.getPrincipal();
                Object details = authentication.getDetails();
                return new ResponseEntity<>(username , HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>("No logged in User", HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Unable to fetch", HttpStatus.NOT_FOUND);
    }
}
