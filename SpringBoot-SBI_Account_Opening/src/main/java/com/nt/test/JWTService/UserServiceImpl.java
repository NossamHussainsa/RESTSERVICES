package com.nt.test.JWTService;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.test.Exception.CustomException;
import com.nt.test.JWTEntity.User;
import com.nt.test.JWTRepository.IUserRepo;
import com.nt.test.JWTTokenCreation.JWTTokenCreation;

@Service
public class UserServiceImpl implements IUserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    private JWTTokenCreation creation;
    
    @Autowired
    private IUserRepo repo;
    
    @Override
    public String registerUser(String email, String password) {
        logger.info("Registering user with email: {}", email);
        
        if (repo.existsByEmail(email)) {
            logger.warn("User with email {} already exists.", email);
            return "UserName " + email + " is already Exists. Please login.";
        } else {
            User user = new User(email, password);
            String token = creation.getGeneratedToken(user.getEmail());
            User data = repo.save(user);
            logger.debug("Generated token for user {}: {}", email, token);
            this.setToken(user.getUsername(), token);
            logger.info("User {} registered successfully.", email);
            return "User " + data.getEmail() + " registered successfully!";
        }
    }

    @Override
    public User validateUser(String email, String password) {
        logger.info("Validating user with email: {}", email);
        
        if (repo.existsByEmail(email)) {
            User user = repo.findByEmailAndPassword(email, password);
            if (user != null && password.equals(user.getPassword())) {
                logger.info("User validated successfully for email: {}", email);
                return user;
            } else {
                logger.warn("Invalid password for email: {}", email);
                return null;
            }
        } else {
            logger.warn("User with email {} not found.", email);
            return null;
        }
    }

    @Override
    public void setToken(String username, String token) {
        logger.debug("Setting token for user {}: {}", username, token);
        User user = repo.findByEmail(username);
        user.setToken(token);
        repo.save(user);
        logger.info("Token set successfully for user {}", username);
    }

    @Override
    public User getToken(String username, String password) {
        logger.info("Getting token for user with email: {}", username);
        User user = repo.findByEmailAndPassword(username, password);
        if (user != null && password.equals(user.getPassword())) {
            logger.info("Token retrieved successfully for user {}", username);
            return user;
        } else {
            logger.warn("Failed to retrieve token for user {}: invalid credentials", username);
            return null;
        }
    }

    @Override
    public User findbyUser(String username) {
        logger.info("Finding user by username: {}", username);
        return repo.findByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Loading user by email: {}", email);
        User user = repo.findByEmail(email);
        if (user == null) {
            logger.error("User not found with email: {}", email);
            throw new CustomException("User not found");
        }
        logger.info("User loaded successfully for email: {}", email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }
}
