package com.redesocial.RedeSocial.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Vitor Alex
 * @author Arthur Leandro
 * @since 1.0
 *
 */

@Entity
@Table(name = "tb_user")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String token;

	@NotBlank
	@Size(min = 3, max = 15)
	private String password;

	@OneToMany(mappedBy = "fkUser", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("fkUser")
	private List<PostModel> post;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<PostModel> getPost() {
		return post;
	}

	public void setPost(List<PostModel> post) {
		this.post = post;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
