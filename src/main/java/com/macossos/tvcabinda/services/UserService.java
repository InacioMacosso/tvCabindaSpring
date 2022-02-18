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
import com.macossos.tvcabinda.services.exceptions.DataIntegrityViolationException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User create(@Valid UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);
		validarPorEmailAndTelefone(usuarioDTO);
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
		validarPorEmailAndTelefone(usuarioDTO);
		oldUser = new User(usuarioDTO);
		return userRepository.save(oldUser);
	}

	public void delete(Integer id) {
		User user = findById(id);
		userRepository.delete(user);
	}

	private void validarPorEmailAndTelefone(UsuarioDTO usuarioDTO) {
		Optional<User> user = userRepository.findByEmail(usuarioDTO.getEmail());
		if (user.isPresent() && user.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado");
		}

		user = userRepository.findByPhone(usuarioDTO.getPhone());
		if (user.isPresent() && user.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException("Telefone ja cadastrado");
		}

	}
}
