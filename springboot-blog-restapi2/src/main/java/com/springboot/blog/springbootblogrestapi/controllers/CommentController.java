package com.springboot.blog.springbootblogrestapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogrestapi.payload.CommentDTO;
import com.springboot.blog.springbootblogrestapi.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("posts/{postId}/comments")
	public CommentDTO createNewComment(@PathVariable Long postId,@Valid @RequestBody CommentDTO commentDTO) {
		return commentService.createComment(postId, commentDTO);
	}
	
	@GetMapping("posts/{postId}/comments")
	public List<CommentDTO> getAllComments(@PathVariable Long postId) {
		return commentService.getAllCommentsByPostId(postId);
	}
	
	@GetMapping("posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> getCommentByID(@PathVariable Long postId,
													 @PathVariable Long commentId) {
		CommentDTO commentDTO=commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDTO,HttpStatus.OK);
	}
	
	@PutMapping("posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable Long postId,
													@PathVariable Long commentId,
													@Valid @RequestBody CommentDTO commentDTO) {
		CommentDTO updateComment = commentService.updateComment(postId,commentId,commentDTO);
		return new ResponseEntity<>(updateComment,HttpStatus.OK);
	}
	
	@DeleteMapping("posts/{postId}/comments/{commentId}")
	public String deleteExCommentById(@PathVariable Long postId,
									  @PathVariable Long commentId) {
		commentService.deleteById(postId, commentId);
		return new String("Successfully Deleted");
		
	}
}
