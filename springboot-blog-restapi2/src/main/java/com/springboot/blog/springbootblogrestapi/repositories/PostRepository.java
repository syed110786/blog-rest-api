package com.springboot.blog.springbootblogrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.springbootblogrestapi.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

}
