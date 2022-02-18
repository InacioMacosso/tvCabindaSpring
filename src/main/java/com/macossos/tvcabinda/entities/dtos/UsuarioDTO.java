package com.macossos.tvcabinda.entities.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.macossos.tvcabinda.entities.User;
import com.macossos.tvcabinda.entities.enums.Role;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message = "O campo NOME é requerido")
	private String firstName;
	@NotNull(message = "O campo SOBRENOME é requerido")
	private String lastName;
	@NotNull(message = "O campo EMAIL é requerido")
	private String email;
	@NotNull(message = "O campo PASSWORD é requerido")
	private String password;
	private String phone;
	protected Set<Integer> roles = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(User obj) {
		super();
		this.id = obj.getId();
		this.firstName = obj.getFirstName();
		this.lastName = obj.getLastName();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.phone = obj.getPhone();
		this.roles = obj.getRoles().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Role> getRoles() {
		return roles.stream().map(x -> Role.toEnum(x)).collect(Collectors.toSet());
	}

	public void addRole(Role role) {
		this.roles.add(role.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
