package com.springboot.blog.springbootblogrestapi.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
public class PostDTO {

	private Long id;
	
	@NotEmpty
	@Size(min=2,message="Post title should have at least 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min=10,message="Post description should have at least 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private Set<CommentDTO> comments;
	
	

	public Set<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDTO> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
