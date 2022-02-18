package com.macossos.tvcabinda.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.macossos.tvcabinda.entities.User;
import com.macossos.tvcabinda.entities.dtos.UsuarioDTO;
import com.macossos.tvcabinda.services.CategoryService;
import com.macossos.tvcabinda.services.UserService;

@RestController
@RequestMapping(value = "/usuarios")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		User newObj = userService.create(usuarioDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(user));
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<User> users = userService.findAll();
		List<UsuarioDTO> usuarioDTOs = users.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usuarioDTOs);
	}

}
