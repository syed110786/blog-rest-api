package com.springboot.blog.springbootblogrestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogrestapi.payload.JWTAuthResponse;
import com.springboot.blog.springbootblogrestapi.payload.LoginDTO;
import com.springboot.blog.springbootblogrestapi.payload.RegisterDTO;
import com.springboot.blog.springbootblogrestapi.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	//Build Login REST API
	@PostMapping(value={"/login","/signin"})
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO) {
		String token=authService.login(loginDTO);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}
	
	@PostMapping(value= {"/register","/signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
		String response = authService.register(registerDTO);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
