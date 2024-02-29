package com.example.dcfitness.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
	
	List<Video> findByTitle (String title);
	List<Video> findByAuthor (String author);
	List<Video> findByTitleContaining (String title);
}
