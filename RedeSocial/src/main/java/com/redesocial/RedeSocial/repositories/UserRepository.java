package com.redesocial.RedeSocial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redesocial.RedeSocial.models.UserModel;

/**
 * 
 * @author Laiane Karla
 * @since 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	public List<UserModel> findAllByNameContainingIgnoreCase(String name);

}
