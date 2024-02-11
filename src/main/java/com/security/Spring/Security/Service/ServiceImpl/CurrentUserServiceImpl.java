package com.security.Spring.Security.Service.ServiceImpl;

import com.security.Spring.Security.Service.CurrentUserService;
import com.security.Spring.Security.Util.AppData;
import com.security.Spring.Security.model.MyUser;
import com.security.Spring.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CurrentUserServiceImpl implements CurrentUserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<AppData> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppData<MyUser> objectAppData = new AppData<>();
        try {
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails){
                UserDetails username = (UserDetails) authentication.getPrincipal();
                Object details = authentication.getDetails();
                String username1 = username.getUsername();
                Optional<MyUser> byUsername = userRepository.findByUsername(username1);
                MyUser user = byUsername.get();
                objectAppData.setMessage("Fetched Successfully");
                objectAppData.setData(user);
                return new ResponseEntity<>(objectAppData , HttpStatus.FOUND);
            }
            else {
                objectAppData.setMessage("No Logged in user");
                return new ResponseEntity<>(objectAppData, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        objectAppData.setMessage("Unable to fetch");
        return new ResponseEntity<>(objectAppData, HttpStatus.NOT_FOUND);
    }
}
