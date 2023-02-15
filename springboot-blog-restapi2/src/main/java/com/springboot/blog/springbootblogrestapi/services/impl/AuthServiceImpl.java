package com.springboot.blog.springbootblogrestapi.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogrestapi.entities.Role;
import com.springboot.blog.springbootblogrestapi.entities.User;
import com.springboot.blog.springbootblogrestapi.exceptions.BlogAPIException;
import com.springboot.blog.springbootblogrestapi.payload.LoginDTO;
import com.springboot.blog.springbootblogrestapi.payload.RegisterDTO;
import com.springboot.blog.springbootblogrestapi.repositories.RoleRepository;
import com.springboot.blog.springbootblogrestapi.repositories.UserRepository;
import com.springboot.blog.springbootblogrestapi.security.JwtTokenProvider;
import com.springboot.blog.springbootblogrestapi.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider=jwtTokenProvider;
	}

	@Override
	public String login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameByEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		//return "User Logged In successfully";
		return token;
	}

	@Override
	public String register(RegisterDTO registerDTO) {
		// TODO Auto-generated method stub
		if(userRepository.existsByUsername(registerDTO.getUsername())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username is already exists!"); 
		}
		if(userRepository.existsByEmail(registerDTO.getEmail())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Email is already exists");
		}
		User user = new User();
		user.setName(registerDTO.getEmail());
		user.setUsername(registerDTO.getUsername());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
		return "User registered successfully";
	}

}
