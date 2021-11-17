package com.redesocial.RedeSocial.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @author Lauro
 * @since 1.0
 *
 */

public class UserLoginDTO {

	private @NotBlank(message = "Insert valid email") @Email String email;

	private @NotBlank(message = "Insert valid passWord") @Size(min = 3, max = 15) String passWord;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
