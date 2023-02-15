package com.springboot.blog.springbootblogrestapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.springbootblogrestapi.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String Username,String email);
	
	Optional<User> findByUsername(String Username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
