package com.springboot.blog.springbootblogrestapi.services;

import com.springboot.blog.springbootblogrestapi.payload.LoginDTO;
import com.springboot.blog.springbootblogrestapi.payload.RegisterDTO;

public interface AuthService {
	String login(LoginDTO loginDTO);
	
	String register(RegisterDTO registerDTO);
}
