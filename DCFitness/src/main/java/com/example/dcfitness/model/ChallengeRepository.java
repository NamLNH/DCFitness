package com.example.dcfitness.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
	List<Challenge> findByTitle (String title);
	List<Challenge> findByTitleContaining (String subTitle);
	List<Challenge> findByDescription (String description);
	List<Challenge> findByDescriptionContaining (String subDescription);
}
