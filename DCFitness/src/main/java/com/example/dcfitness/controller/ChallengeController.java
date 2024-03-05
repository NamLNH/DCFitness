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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcfitness.model.BodyPart;
import com.example.dcfitness.model.Challenge;
import com.example.dcfitness.model.ChallengeRepository;
import com.example.dcfitness.model.Comment;
import com.example.dcfitness.model.CommentRepository;
import com.example.dcfitness.model.User;
import com.example.dcfitness.model.UserRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class ChallengeController {
	
	@Autowired
	ChallengeRepository challengeRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	//GET API for all challenges
	@GetMapping("/challenges")
	public ResponseEntity<List<Challenge>> getChallenges() {
		try {
			List<Challenge> challenges = new ArrayList<Challenge>();
				
			challengeRepo.findAll().forEach(challenges::add);
			
			if(challenges.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(challenges, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Add challenge
	@PostMapping("/challenges")
	public ResponseEntity<Challenge> addChallenge(@RequestBody Challenge challenge) {
		try {
			Challenge newChallenge = new Challenge(challenge.getTitle(), challenge.getDescription());
			challengeRepo.save(newChallenge);
			return new ResponseEntity<>(newChallenge, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Delete challenge
	@DeleteMapping("challenges/{challengeId}")
	public ResponseEntity<Challenge> deleteChallenge(@PathVariable("challengeId") Long id) {
		try {
			challengeRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Edit challenge
	@PutMapping("/challenges/{challengeId}")
	public ResponseEntity<Challenge> editChallenge(@PathVariable("challengeId") Long id, @RequestBody Challenge challenge) {
		try {
			Optional<Challenge> _challenge = challengeRepo.findById(id);
			if(_challenge.isPresent()) {
				Challenge newChallenge = _challenge.get();
				newChallenge.setTitle(challenge.getTitle());
				newChallenge.setDescription(challenge.getDescription());
				challengeRepo.save(newChallenge);
				return new ResponseEntity<>(newChallenge, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// User Join challenge
	@PutMapping("/challenges/{challengeId}/users/{userId}")
	public ResponseEntity<Challenge> assignUser(@PathVariable("challengeId") Long challengeId, @PathVariable("userId") Long userId) {
		try {
			Optional<Challenge> _challenge = challengeRepo.findById(challengeId);
			Optional<User> _user = userRepo.findById(userId);
			if(_challenge.isPresent() && _user.isPresent()) {
				Challenge challenge = _challenge.get();
				User user = _user.get();
				challenge.assignUser(user);
				return new ResponseEntity<>(challengeRepo.save(challenge),HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}