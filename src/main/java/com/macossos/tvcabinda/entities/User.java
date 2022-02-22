package com.macossos.tvcabinda.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.macossos.tvcabinda.entities.dtos.UsuarioDTO;
import com.macossos.tvcabinda.entities.enums.Role;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	private String password;
	private String phone;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ROLES")
	protected Set<Integer> roles = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	@JsonIgnore
	@OneToMany(mappedBy = "autor")
	private List<News> news = new ArrayList<>();

	public User() {
		super();
		addRole(Role.CLIENTE);
	}

	public User(Integer id, String firstName, String lastName, String email, String password, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		addRole(Role.CLIENTE);
	}

	public User(UsuarioDTO ojtDto) {
		super();
		this.id = ojtDto.getId();
		this.firstName = ojtDto.getFirstName();
		this.lastName = ojtDto.getLastName();
		this.email = ojtDto.getEmail();
		this.password = ojtDto.getPassword();
		this.phone = ojtDto.getPhone();
		this.dataCriacao = ojtDto.getDataCriacao();
		this.roles = ojtDto.getRoles().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		addRole(Role.CLIENTE);
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
