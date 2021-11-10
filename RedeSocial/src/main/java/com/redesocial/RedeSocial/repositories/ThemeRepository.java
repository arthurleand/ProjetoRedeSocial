package com.redesocial.RedeSocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redesocial.RedeSocial.models.ThemeModel;

public interface ThemeRepository extends JpaRepository<ThemeModel, Long> {
	
	public List<ThemeModel> findAllByNameContainingIgnoreCase(String name);
	

}
