package com.nt.test.JWTSecurityManagment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nt.test.JWTFilterManagment.JWTFilter;
import com.nt.test.JWTService.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurity.class);

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        logger.info("Creating AuthenticationManager bean");
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Starting securityFilterChain configuration");

        // Disable CSRF
        http.csrf().disable();
        logger.debug("CSRF disabled");

        // Permit access to /register and /login
        logger.info("Configuring authorization rules for /register and /login");
        http.authorizeRequests()
            .requestMatchers("/register", "/login").permitAll();

        // All other requests need authentication
        logger.info("Configuring all other requests to be authenticated");
        http.authorizeRequests()
            .anyRequest().authenticated();

        // Set session creation policy to stateless
        logger.info("Setting session creation policy to STATELESS");
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Adding JWT filter before UsernamePasswordAuthenticationFilter
        logger.info("Adding JWT filter before UsernamePasswordAuthenticationFilter");
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        logger.info("Security configuration completed successfully");
        return http.build();
    }
  
}
