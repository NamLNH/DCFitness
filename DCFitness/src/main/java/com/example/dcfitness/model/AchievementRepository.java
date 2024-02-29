package com.example.dcfitness.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievements, Long> {
	List<Achievements> findById(long id);
	List<Achievements> findByUser(User user);	
}
