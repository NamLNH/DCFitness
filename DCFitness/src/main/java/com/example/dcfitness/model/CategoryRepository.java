package com.example.dcfitness.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Optional<Category> findByName (String name);
	Optional<Category> findByNameContaining (String name);
	
}
