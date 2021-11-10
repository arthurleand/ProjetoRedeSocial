package com.redesocial.RedeSocial.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_theme")
public class ThemeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTheme;

	@NotBlank
	@Size(min = 5, max = 100)
	private String name;

	@NotBlank
	@Size(min = 5, max = 500)
	private String descriptionTheme;

	public Long getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptionTheme() {
		return descriptionTheme;
	}

	public void setDescriptionTheme(String descriptionTheme) {
		this.descriptionTheme = descriptionTheme;
	}

}