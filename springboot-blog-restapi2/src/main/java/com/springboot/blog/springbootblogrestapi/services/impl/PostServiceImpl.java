package com.springboot.blog.springbootblogrestapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogrestapi.entities.Post;
import com.springboot.blog.springbootblogrestapi.exceptions.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.payload.PostDTO;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;
import com.springboot.blog.springbootblogrestapi.repositories.PostRepository;
import com.springboot.blog.springbootblogrestapi.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	
	

	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		super();
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> listOfPosts=posts.getContent();
		List<PostDTO> content=listOfPosts.stream().map(post->mapToDTO(post)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setLast(posts.isLast());
		//return postRepository.findAll(pageable).stream().map(post->mapToDTO(post)).collect(Collectors.toList());
		return postResponse;
	}

	@Override
	public PostDTO getPostById(Long id) {
		// TODO Auto-generated method stub
		return postRepository.findById(id).map(post->mapToDTO(post)).orElseThrow(()-> new ResourceNotFoundException("Id",""+id,"404"));
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		// TODO Auto-generated method stub
		Post post=mapToEntity(postDTO);
		Post newPost=postRepository.save(post);
		
		PostDTO postResponse = mapToDTO(newPost);
		return postResponse;
	}

	@Override
	public PostDTO updatePost(Long id, PostDTO postDTO) {
		// TODO Auto-generated method stub
		
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id",""+id,"404"));
		//post.setId(id);
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setDescription(postDTO.getDescription());
		Post updatedPost = postRepository.save(post);
		
		
		return mapToDTO(updatedPost);
		
	}

	@Override
	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		postRepository.deleteById(id);
	}

	private PostDTO mapToDTO(Post post) {
		PostDTO postDTO = modelMapper.map(post,PostDTO.class);
//		PostDTO postDTO = new PostDTO();
//		postDTO.setId(post.getId());
//		postDTO.setTitle(post.getTitle());
//		postDTO.setDescription(post.getDescription());
//		postDTO.setContent(post.getContent());
		return postDTO;
	}
	
	private Post mapToEntity(PostDTO postDTO) {
		Post post = modelMapper.map(postDTO,Post.class);
//		Post post = new Post();
//		post.setTitle(postDTO.getTitle());
//		post.setDescription(postDTO.getDescription());
//		post.setContent(postDTO.getContent());
		return post;
	}
}
