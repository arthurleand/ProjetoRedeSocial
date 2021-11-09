package com.redesocial.RedeSocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redesocial.RedeSocial.models.TemaModel;

public interface TemaRepository extends JpaRepository<TemaModel, Long> {
	
	public List<TemaModel> findAllByNomeContainingIgnoreCase(String nome);
	

}
