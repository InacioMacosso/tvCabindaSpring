package com.macossos.tvcabinda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macossos.tvcabinda.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
