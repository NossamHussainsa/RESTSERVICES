package com.nt.test.JWTSecurityController;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.test.JWTEntity.User;
import com.nt.test.JWTService.IUserService;
import com.nt.test.JWTTokenCreation.JWTTokenCreation;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private JWTTokenCreation creation;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User users) {
        logger.info("Starting user registration for email: {}", users.getEmail());
        
        // Call the user service to handle registration
        String result = userService.registerUser(users.getEmail(), users.getPassword());
        
        logger.info("User registration completed for email: {}", users.getEmail());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User users) {
        logger.info("Attempting login for email: {}", users.getEmail());
        
        // Implement login logic here
        User user = userService.validateUser(users.getEmail(), users.getPassword());
        String validUser = null;
        Date validdate = null;
        
        if (user != null) {
            validUser = creation.getValidUser(user.getToken());
            validdate = creation.getValidateDate(user.getToken());
            logger.debug("User token validation successful for email: {}", users.getEmail());
        } else {
            logger.warn("Invalid login attempt for email: {}", users.getEmail());
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.BAD_REQUEST);
        }

        logger.debug("validUser: {}, validdate: {}", validUser, validdate);
        logger.debug("Current date: {}", new Date());
        
        if (user.getEmail().equals(validUser)) {
            if (!validdate.before(new Date())) {
                logger.info("Login successful for email: {}", user.getEmail());
                return ResponseEntity.ok()
                    .header("Authorization", user.getToken())
                    .body("Login successful! " + user.getEmail());
            } else {
                logger.warn("Token expired for email: {}", user.getEmail());
                String token = creation.getGeneratedToken(users.getEmail());
                userService.setToken(users.getEmail(), token);
                return new ResponseEntity<>("Token expired. Please login again.", HttpStatus.ACCEPTED);
            }
        } else {
            logger.warn("Token validation failed for email: {}", users.getEmail());
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.BAD_REQUEST);
        }
    }
}

