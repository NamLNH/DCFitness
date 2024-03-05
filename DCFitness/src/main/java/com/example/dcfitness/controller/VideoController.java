package com.example.dcfitness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcfitness.model.BodyPart;
import com.example.dcfitness.model.BodyPartRepository;
import com.example.dcfitness.model.Category;
import com.example.dcfitness.model.CategoryRepository;
import com.example.dcfitness.model.User;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class VideoController {
	
	@Autowired
	VideoRepository videoRepository;
	
	//GET API for all videos
	@GetMapping("/videos")
	public ResponseEntity<List<Video>> getAllVideos() {

		try {
			List<Video> videos = new ArrayList<Video>();
			videoRepository.findAll().forEach(videos::add);
			if (videos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(videos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}	
	
//	@GetMapping("/videos/{id}/addFav")
//	public ResponseEntity<User> addFavouriteVideo() {
//		try {
//		} catch (Exception e) {
//			return new ResponseEntity<>()
//		}
//	}
	
	
	//API Get all video with category filter
//	@GetMapping("/videos/category/{categoryId}")
//	public ResponseEntity<List<Video>> getVideosByCategory(@PathVariable("categoryId") Long categoryId){
//		ArrayList<Video> videos = new ArrayList<Video>();
//		try {
//			videoRepository.findByCategoryId(categoryId).forEach(videos::add);
//			if(videos.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(videos, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}	
//		
//	}	
	
	
//	//API Assign body part to video
//	@PutMapping("/videos/{videoId}/bodyparts/{bodyPartId}")
//	public ResponseEntity<Video> assignBodyPart(
//			@PathVariable("videoId") Long videoId,
//			@PathVariable("bodyPartId") Long bodyPartId) {
//		
//		try {
//			Optional<Video> _video = videoRepository.findById(videoId);
//			Optional<BodyPart> _part = bodyPartRepository.findById(bodyPartId);
//			if(_video.isPresent() && _part.isPresent()) {
//				Video video = _video.get();
//				BodyPart part = _part.get();
////				video.assignBodyPart(part);
//				return new ResponseEntity<>(videoRepository.save(video),HttpStatus.OK);
//			}
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			
//		} catch (Exception e) {
//			System.out.println("Error in query video and body part");
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	

}
