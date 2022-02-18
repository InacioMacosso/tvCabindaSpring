package com.macossos.tvcabinda.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.entities.User;
import com.macossos.tvcabinda.entities.dtos.UsuarioDTO;
import com.macossos.tvcabinda.repositories.UserRepository;
import com.macossos.tvcabinda.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User create(@Valid UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);
		User user = new User(usuarioDTO);
		return userRepository.save(user);
	}

	public User findById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado! id: " + id));
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User update(Integer id, @Valid UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(id);
		User oldUser = findById(id);
		oldUser = new User(usuarioDTO);
		return userRepository.save(oldUser);
	}

	public void delete(Integer id) {
		User user = findById(id);
		userRepository.delete(user);
	}
}
