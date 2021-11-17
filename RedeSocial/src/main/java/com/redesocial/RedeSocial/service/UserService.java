package com.redesocial.RedeSocial.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.redesocial.RedeSocial.dtos.UserLoginDTO;
import com.redesocial.RedeSocial.models.UserModel;
import com.redesocial.RedeSocial.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public UserModel RegisterUser (UserModel user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String passwordEncoder = encoder.encode(user.getPassword());
		user.setPassword(passwordEncoder);
		
		return repository.save(user);
		
	}
	
	public Optional<UserLoginDTO> Logar(Optional<UserLoginDTO> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UserModel> userModel = repository.findByEmail(user.get().getEmail());
		
		if(userModel.isPresent()) {
			if(encoder.matches(user.get().getPassWord(),user.get().getPassWord())) {
				
				String auth = user.get().getEmail() + ":" + user.get().getPassWord();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setEmail(userModel.get().getEmail());
				
				return user;
			}
		}
	
	return null;
	}
}