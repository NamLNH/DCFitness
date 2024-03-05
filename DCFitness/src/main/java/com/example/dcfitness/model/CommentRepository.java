package com.example.dcfitness.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByContent (String content);
	List<Comment> findByContentContaining (String content);
	List<Comment> findByVideoId (Long videoId);
	List<Comment> findByUserId (Long userId);
}
