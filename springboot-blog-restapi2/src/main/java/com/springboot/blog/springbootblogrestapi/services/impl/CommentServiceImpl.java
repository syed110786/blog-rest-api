package com.springboot.blog.springbootblogrestapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogrestapi.entities.Comment;
import com.springboot.blog.springbootblogrestapi.entities.Post;
import com.springboot.blog.springbootblogrestapi.exceptions.BlogAPIException;
import com.springboot.blog.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.payload.CommentDTO;
import com.springboot.blog.springbootblogrestapi.repositories.CommentRepository;
import com.springboot.blog.springbootblogrestapi.repositories.PostRepository;
import com.springboot.blog.springbootblogrestapi.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final ModelMapper modelMapper; 
	
	

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
			ModelMapper modelMapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CommentDTO createComment(long postId, CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		Comment comment = mapToEntity(commentDTO);
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",""+postId));
		comment.setPost(post);
		Comment newComment=commentRepository.save(comment);
		return mapToDTO(newComment);
	}
	
	private CommentDTO mapToDTO(Comment comment) {
		CommentDTO commentDTO = modelMapper.map(comment,CommentDTO.class);
//		CommentDTO commentDTO = new CommentDTO();
//		commentDTO.setId(comment.getId());
//		commentDTO.setName(comment.getName());
//		commentDTO.setEmail(comment.getEmail());
//		commentDTO.setBody(comment.getBody());
		return commentDTO;
	}

	private Comment mapToEntity(CommentDTO commentDTO) {
		Comment comment = modelMapper.map(commentDTO, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentDTO.getId());
//		comment.setBody(commentDTO.getBody());
//		comment.setEmail(commentDTO.getEmail());
//		comment.setName(commentDTO.getName());
		return comment;
	}

	@Override
	public List<CommentDTO> getAllCommentsByPostId(long postId) {
		// TODO Auto-generated method stub
		
		return commentRepository.findByPostId(postId).stream().map(comment->mapToDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentById(long postId, long commentId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",""+postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",""+commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Doesnot belong to Post");
		}
		return mapToDTO(comment);
	}

	@Override
	public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",""+postId));
		//post.setId(postId);
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",""+commentId));
		//comment.setId(commentId);
		
		comment.setName(commentDTO.getName());
		comment.setBody(commentDTO.getBody());
		comment.setEmail(commentDTO.getEmail());
		
		Comment commentUpdated = commentRepository.save(comment);
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Doesnot belong to Post");
		}
		return mapToDTO(commentUpdated);
	}

	@Override
	public void deleteById(long postId, long commentId) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",""+postId));
		//post.setId(postId);
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",""+commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment Doesnot belong to Post");
		}
	   commentRepository.delete(comment);
	}
}
