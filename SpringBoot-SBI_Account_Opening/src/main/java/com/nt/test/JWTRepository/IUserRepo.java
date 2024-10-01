package com.nt.test.JWTRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.test.JWTEntity.User;

public interface IUserRepo extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String username);

	boolean existsByEmail(String email);


}
