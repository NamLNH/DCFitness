package com.example.dcfitness.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	List<Video> findByName (String name);
	List<Video> findByNameContaining (String name);
	
}
