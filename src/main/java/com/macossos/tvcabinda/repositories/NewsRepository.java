package com.macossos.tvcabinda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macossos.tvcabinda.entities.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

}
