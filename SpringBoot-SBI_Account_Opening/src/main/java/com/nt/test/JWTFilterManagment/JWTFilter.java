package com.nt.test.JWTFilterManagment;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.nt.test.Exception.CustomException;
import com.nt.test.JWTEntity.User;
import com.nt.test.JWTService.IUserService;
import com.nt.test.JWTTokenCreation.JWTTokenCreation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    @Autowired
    private JWTTokenCreation jwtTokenCreation;

    @Autowired
    private IUserService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Extract the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        logger.info("Processing request URI: {}", request.getRequestURI());
        logger.info("Processing request authorizationHeader: {}", authorizationHeader);
        // Check if the Authorization header is present and valid
        if (authorizationHeader != null) {
            token = authorizationHeader; // Extract the token
            logger.info("Processing request token: {}", token);
            try {
                // Extract email from the token
                email = jwtTokenCreation.getValidUser(token);
                logger.info("Extracted email from token: {}", email);

                // Proceed only if email is present and user is not already authenticated
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    logger.info("Fetching user from the database for email: {}", email);

                    // Fetch user from the database
                    User user = userDetailsService.findbyUser(email);

                    if (user != null && token.equals(user.getToken())) { 
                        logger.info("Token is valid. Setting authentication for user: {}", email);

                        // Load user details and create an authentication object
                        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                        if (userDetails != null) {
                            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            logger.info("authenticationToken : {}", authenticationToken.getAuthorities().toString());
                            logger.info("Authentication set for user: {}", email);
                        }
                    } else {
                        logger.warn("Token validation failed for email: {}", email);
                    }
                } else if (email != null) {
                    logger.info("User is already authenticated: {}", email);
                }
            } catch (Exception e) {
                logger.error("Error while validating token: {}", e.getMessage());
                throw new CustomException("Token validation error: " + e.getMessage());
            }
        } else {
            logger.warn("No JWT token found in the Authorization header");
        }

        logger.info("Finished processing request for URI: {}", request.getRequestURI());
        chain.doFilter(request, response);
    }
}
