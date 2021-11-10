package com.redesocial.RedeSocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<PostModel, Long> {
	
	public List<PostModel> findAllByNameContainingIgnoreCase(String name);
	

}
