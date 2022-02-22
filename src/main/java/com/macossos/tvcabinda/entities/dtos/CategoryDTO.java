package com.macossos.tvcabinda.entities.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.macossos.tvcabinda.entities.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message = "O campo NOME é requerido")
	private String name;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(Category category) {
		super();
		this.id = category.getId();
		this.name = category.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
