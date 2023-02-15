package com.springboot.blog.springbootblogrestapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.springbootblogrestapi.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

	Optional<Role> findByName(String name);
}
