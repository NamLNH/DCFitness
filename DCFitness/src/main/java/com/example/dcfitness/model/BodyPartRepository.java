package com.example.dcfitness.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyPartRepository extends JpaRepository<BodyPart, Long> {
	
	Optional<BodyPart> findByName (String name);
	Optional<BodyPart> findByNameContaining (String name);
	
}
