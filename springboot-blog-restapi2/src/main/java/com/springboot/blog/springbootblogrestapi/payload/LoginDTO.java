package com.springboot.blog.springbootblogrestapi.payload;


public class LoginDTO {

	private String usernameByEmail;
	private String password;
	
	
	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public LoginDTO(String usernameByEmail, String password) {
		super();
		this.usernameByEmail = usernameByEmail;
		this.password = password;
	}


	public String getUsernameByEmail() {
		return usernameByEmail;
	}
	public void setUsernameByEmail(String usernameByEmail) {
		this.usernameByEmail = usernameByEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
