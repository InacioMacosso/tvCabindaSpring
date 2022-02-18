package com.macossos.tvcabinda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macossos.tvcabinda.entities.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

}
