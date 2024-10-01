package com.nt.test.JWTTokenCreation;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenCreation {
	private final static String Security_Key = "fvbdfvbdfbdfbfdbkjdfbjkvnbjkvj vcjb jvckjfhvdjfbjk";
	private final static long Experidate = 24 * 60 * 60 * 1000;

	public String getGeneratedToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Experidate))
				.signWith(SignatureAlgorithm.HS512, Security_Key)
				.compact();
	}

	public String getValidUser(String token) {
		return Jwts.parser()
				.setSigningKey(Security_Key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();

	}

	public Date getValidateDate(String token) {
		return Jwts.parser()
				.setSigningKey(Security_Key)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
	}

}
