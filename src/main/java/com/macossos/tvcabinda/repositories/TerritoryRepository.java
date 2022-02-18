package com.macossos.tvcabinda.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macossos.tvcabinda.entities.Territory;

@Repository
public interface TerritoryRepository extends JpaRepository<Territory, Integer> {

	Optional<Territory> findByName(String name);

}
