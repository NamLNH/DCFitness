package com.example.dcfitness.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class VideoController {
	
	@Autowired
	VideoRepository videoRepo;
	
	//GET API for all videos
	@GetMapping("/videos")
	public ResponseEntity<List<Video>> getAllCourses(@RequestParam(name = "title", required = false) String title) {

		try {
			List<Video> videos = new ArrayList<Video>();

			if (title == null) {
				videoRepo.findAll().forEach(videos::add);

			} else {
				videoRepo.findByTitle(title).forEach(videos::add);
			}

			if (videos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(videos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
