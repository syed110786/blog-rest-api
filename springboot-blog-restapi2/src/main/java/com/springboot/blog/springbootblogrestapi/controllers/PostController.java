package com.springboot.blog.springbootblogrestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogrestapi.payload.PostDTO;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;
import com.springboot.blog.springbootblogrestapi.services.PostService;
import com.springboot.blog.springbootblogrestapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDTO> createNewPost(@Valid @RequestBody PostDTO postDTO) {
		return new ResponseEntity<>(postService.createPost(postDTO),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<PostResponse> getPosts(@RequestParam(value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
												  @RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required=false) int pageSize,
												  @RequestParam(value="sortBy",defaultValue=AppConstants.DEFAULT_SORT_BY,required=false) String sortBy,
												  @RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIRECTION,required=false) String sortDir) {
		return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PostDTO> getExPostById(@PathVariable Long id) {
		return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDTO> updateExPost( @PathVariable Long id,@Valid @RequestBody PostDTO postDTO) {
		return new ResponseEntity<>(postService.updatePost(id, postDTO),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteExPost(@PathVariable Long id) {
		 postService.deletePost(id);
	}
}
