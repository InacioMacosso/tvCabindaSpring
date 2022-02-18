package com.macossos.tvcabinda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.entities.User;
import com.macossos.tvcabinda.entities.enums.Role;
import com.macossos.tvcabinda.repositories.UserRepository;

@Service
public class DBService {
	@Autowired
	private UserRepository userRepository;

	public void instanciaDB() {
		User user1 = new User(null, "Inacio", "Macosso", "inaciomacosso@yahoo.com", "password", "0102034545");
		user1.addRole(Role.ADMIN);
		userRepository.save(user1);
	}
}
