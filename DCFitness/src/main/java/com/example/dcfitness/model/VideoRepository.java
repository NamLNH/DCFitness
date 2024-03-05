package com.example.dcfitness.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
	
	Optional<Video> findById(Long id);
	List<Video> findByTitle (String title);
	List<Video> findByAuthor (String author);
	List<Video> findByTitleContaining (String title);
//	List<Video> findByCategoryId (Long categoryId);
}
