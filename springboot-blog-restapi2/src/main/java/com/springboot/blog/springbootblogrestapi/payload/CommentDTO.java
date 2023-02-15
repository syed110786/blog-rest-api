package com.springboot.blog.springbootblogrestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDTO {

	private long id;
	
	@NotEmpty(message="Name should not be null or empty")
	private String name;
	
	@Email
	@NotEmpty(message="Email should not be null or empty")
	private String email;
	
	@NotEmpty
	@Size(min=10,message="Comment Body must be minimum 10 characters")
	private String body;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getBody() {
		return body;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
