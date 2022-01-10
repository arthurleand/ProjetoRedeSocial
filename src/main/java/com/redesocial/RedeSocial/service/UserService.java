package com.redesocial.RedeSocial.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.redesocial.RedeSocial.dtos.UserRegistrationDTO;
import com.redesocial.RedeSocial.dtos.UserLoginDTO;
import com.redesocial.RedeSocial.models.UserModel;
import com.redesocial.RedeSocial.repositories.UserRepository;

/**
 * 
 * @author Vitor Alex
 * @author Arthur Leandro
 * @since 1.1
 *
 */

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	
	private static String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	
	private static String generatorBasicToken(String email, String password) {
		String structure = email + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structureBase64);
	}

	public ResponseEntity<UserModel> registerUser(@Valid UserRegistrationDTO newUser) {
		
		Optional<UserModel> user = repository.findByEmail(newUser.getEmail());
		if (user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
		} else {
			UserModel userNew = new UserModel();
			userNew.setName(newUser.getName());
			userNew.setEmail(newUser.getEmail());
			userNew.setPassword(encryptPassword(newUser.getPassword()));
			userNew.setFoto(newUser.getFoto());
			return ResponseEntity.status(201).body(repository.save(userNew));
		}
	}
	
	public Optional<UserModel> updateUser(UserModel user){
		
		if(repository.findById(user.getId()).isPresent()) {
			Optional<UserModel> findUser = repository.findById(user.getId());
			
			if(findUser.isPresent()) {
			if(findUser.get().getId() != user.getId())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			}
			
			user.setPassword(encryptPassword(user.getPassword()));
			return Optional.of(repository.save(user));
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
	}
	
	
	public Optional<UserLoginDTO> Login(Optional<UserLoginDTO> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UserModel> userModel = repository.findByEmail(user.get().getEmail());
		
		if(userModel.isPresent()) {
			if(encoder.matches(user.get().getPassword(),userModel.get().getPassword())) {

				user.get().setToken(generatorBasicToken(user.get().getEmail(),user.get().getPassword()));
				user.get().setEmail(userModel.get().getEmail());
				user.get().setName(userModel.get().getName());
				user.get().setFoto(userModel.get().getFoto());
				user.get().setId(userModel.get().getId());
				user.get().setPassword(userModel.get().getPassword());
				
				
				return user;
			}
		}
	
	return null;
	}
}