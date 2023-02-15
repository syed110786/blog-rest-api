package com.springboot.blog.springbootblogrestapi.services;

import com.springboot.blog.springbootblogrestapi.payload.PostDTO;
import com.springboot.blog.springbootblogrestapi.payload.PostResponse;

public interface PostService {

	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir);
	PostDTO getPostById(Long id);
	PostDTO createPost(PostDTO postDTO);
	PostDTO updatePost(Long id,PostDTO postDTO);
	void deletePost(Long id);
	
}
