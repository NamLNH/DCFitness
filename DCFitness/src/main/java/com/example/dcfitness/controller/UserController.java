package com.example.dcfitness.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.example.dcfitness.model.User;
import com.example.dcfitness.model.UserRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToMany;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private VideoRepository vidRepo;

	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable("id") long id){
		try {
			userRepo.deleteById(id);
		} catch (Exception err) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
		Optional<User> userData = userRepo.findById(id);
		
		if(userData.isPresent()) {
			User editedUser = userData.get();
			editedUser.setUsername(user.getUsername());
			editedUser.setPassword(user.getPassword());
			editedUser.setEmail(user.getEmail());
			return new ResponseEntity<>(userRepo.save(editedUser),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id){
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	 
	//How to register a new user:
	@PostMapping("/users/signup")
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User newUser = new User(user.getUsername(),user.getPassword(),user.getEmail());
			userRepo.save(newUser);
			return new ResponseEntity<>(newUser,HttpStatus.CREATED);
		} catch (Exception err) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(
			@RequestParam(required=false) String username){
		try {
			List<User> users = new ArrayList<User>();
			
			if(username == null) {
				userRepo.findAll().forEach(users::add);
			} else {
				userRepo.findByUsername(username).ifPresent(users::add);
			}
			
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(users,HttpStatus.OK);
			
		} catch (Exception err) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	//Adds Video as Favorite to User as per ID of user and video both
//	@PutMapping("users/{userId}/addFav/{videoId}")
//	public ResponseEntity<User> addFavourite(
//			@PathVariable("userId") long userId,
//			@PathVariable("videoId") long videoId
//	) {
//
//		Optional<User> user = userRepo.findById(userId);
//		if(user.isPresent()) {
//			Optional<Video> video = vidRepo.findById(videoId);
//			if (video.isPresent()) {
//				user.get().addFavoriteVideo(video.get());
//				userRepo.save(user.get());
//				return new ResponseEntity<>(user.get(),HttpStatus.ACCEPTED);
//			}
//			//Video ID is invalid
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		//User ID is invalid
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		
//	}
	
	//API Assign body part to video
		@PutMapping("/users/{userId}/addFav/{videoId}")
		public ResponseEntity<User> addFavourite(
				@PathVariable("userId") long userId,
				@PathVariable("videoId") long videoId)
		{
			try {
				Optional<User> _user = userRepo.findById(userId);
				Optional<Video> _video = vidRepo.findById(videoId);
				if(_video.isPresent() && _user.isPresent()) {
					Video video = _video.get();
					User user = _user.get();
					user.assignFavVideo(video);
					return new ResponseEntity<>(userRepo.save(user),HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			} catch (Exception e) {
				System.out.println("Error in query user and video");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	

}
