package com.redesocial.RedeSocial.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redesocial.RedeSocial.dtos.UserLoginDTO;
import com.redesocial.RedeSocial.dtos.UserRegistrationDTO;
import com.redesocial.RedeSocial.models.UserModel;
import com.redesocial.RedeSocial.repositories.UserRepository;
import com.redesocial.RedeSocial.service.UserService;

/**
 * 
 * @author Arthur Leandro
 * @since 2.0
 *
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserModel> getById(@PathVariable Long id) {
		return userRepository.findById(id)
		        .map(resp -> ResponseEntity.ok(resp))
		        .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO> Autentication(@Valid @RequestBody Optional<UserLoginDTO> user){
		return userService.Login(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	@PostMapping("/register")
	public ResponseEntity<UserModel> Post(@Valid @RequestBody UserRegistrationDTO user){
		return userService.registerUser(user);
	}
	@PutMapping("/update")
	public ResponseEntity<UserModel> putUser(@Valid @RequestBody UserModel user){
		return userService.updateUser(user).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
		
}
