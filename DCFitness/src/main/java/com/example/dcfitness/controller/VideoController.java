package com.example.dcfitness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcfitness.model.Category;
import com.example.dcfitness.model.CategoryRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class VideoController {
	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//GET API for all videos
	@GetMapping("/videos")
	public ResponseEntity<List<Video>> getAllCourses() {

		try {
			List<Video> videos = new ArrayList<Video>();
			videoRepository.findAll().forEach(videos::add);
			if (videos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(videos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	//API Get all video with category filter
	@GetMapping("/videos/category/{category}")
	public ResponseEntity<List<Video>> getVideosByCategory(@PathVariable("category") String category){
		
		try {
			Optional<Category> categoryReturn = categoryRepository.findByName(category);
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	//API for POST new video

}
