package com.springboot.blog.springbootblogrestapi.services;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.payload.CommentDTO;

public interface CommentService {

	List<CommentDTO> getAllCommentsByPostId(long postId);
	CommentDTO updateComment(long postId,long commentId, CommentDTO commentDTO);
	CommentDTO getCommentById(long postId,long commentId);
	CommentDTO createComment(long postId, CommentDTO commentDTO);
	void deleteById(long postId,long commentId);
}
