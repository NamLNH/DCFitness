package com.example.dcfitness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcfitness.model.Comment;
import com.example.dcfitness.model.CommentRepository;
import com.example.dcfitness.model.User;
import com.example.dcfitness.model.UserRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	VideoRepository videoRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	//GET API for comments by video
	@GetMapping("/comments/{videoId}")
	public ResponseEntity<List<Comment>> getCommentByVideo(@PathVariable("videoId") Long videoId) {
		try {
			List<Comment> comments = new ArrayList<Comment>();
			
			commentRepo.findByVideoId(videoId).forEach(comments::add);
			
			if(comments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(comments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Add comment by user
	@PostMapping("/comments/{videoId}/{userId}")
	public ResponseEntity<Comment> addCommentByVideo(@PathVariable("videoId") Long videoId, @PathVariable("userId") Long userId, @RequestBody Comment comment) {
		try {
			Comment newComment = getComment(videoRepo, userRepo, videoId, userId, comment);
			commentRepo.save(newComment);
			return new ResponseEntity<>(newComment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete comment by id
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Comment> deleteCommentById(@PathVariable("id") Long id) {
		try {
			commentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Edit Comment by id
	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment comment) {
		try {
			Optional<Comment> commentData = commentRepo.findById(id);
			
			if(commentData.isPresent()) {
				Comment _comment = commentData.get();
				_comment.setContent(comment.getContent());
				commentRepo.save(_comment);
				return new ResponseEntity<>(_comment, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * Checking logic for comment
	 */
	public static Comment getComment(VideoRepository videoRepo, UserRepository userRepo, Long videoId, Long userId, Comment comment) {
		try {
			Optional<Video> _video = videoRepo.findById(videoId);
			Optional<User> _user = userRepo.findById(userId);
			
			
			if(_video.isPresent())
			{
				comment.setVideo(_video.get());
			} else {
				System.out.println("Cant find the video in list");
			}
			
			if(_user.isPresent())
			{
				comment.setUser(_user.get());
			} else {
				System.out.println("Cant find the user in list");
			}
		} catch (Exception e) {
			System.out.println("Error in getting video and user from database");
		}
		return comment;
	}
}