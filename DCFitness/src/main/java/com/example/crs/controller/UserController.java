package com.example.crs.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crs.model.User;
import com.example.crs.model.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
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
	@PostMapping("/users")
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
				userRepo.findByUsername(username).forEach(users::add);
			}
			
			if(users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(users,HttpStatus.OK);
			
		} catch (Exception err) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
