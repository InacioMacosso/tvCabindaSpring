package com.macossos.tvcabinda.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macossos.tvcabinda.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(String phone);

}
