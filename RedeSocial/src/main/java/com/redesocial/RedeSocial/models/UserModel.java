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

import io.swagger.annotations.ApiModelProperty;

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
	private Long id;

	@NotBlank
	private String name;

	@ApiModelProperty(example = "email@email.com.br")
	@NotBlank(message = "O atributo Usuário é Obrigatório!")
	@Email(message = "O atributo Usuário deve ser um email válido!")
	private String email;

	private String foto;

	@NotBlank
	@Size(min = 3, max = 100)
	private String password;

	@OneToMany(mappedBy = "fkUser", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("fkUser")
	private List<PostModel> post;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
