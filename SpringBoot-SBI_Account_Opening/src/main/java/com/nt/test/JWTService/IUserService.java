package com.nt.test.JWTService;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nt.test.JWTEntity.User;

public interface IUserService extends UserDetailsService{
	String registerUser(String email, String password);
	 User validateUser(String email, String password) ;
	void setToken(String username, String token);
	User getToken(String username,String password);
	User findbyUser(String username);
}
