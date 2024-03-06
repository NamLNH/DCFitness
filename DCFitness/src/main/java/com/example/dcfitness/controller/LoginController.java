package com.example.dcfitness.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;


import com.example.dcfitness.model.User;
import com.example.dcfitness.model.UserRepository;
import com.example.dcfitness.request.UserLoginRequest;
import com.example.dcfitness.SecurityLogin.JwtUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	JwtUtils jwtUtils;	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequest loginRequest){
		try {
			Optional<User> userData = userRepo.findByUsername(loginRequest.getUsername());
			if(userData.isPresent()) {
				String password = userData.get().getPassword();
				if (password.equals(loginRequest.getPassword())) {
					ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(loginRequest.getUsername());
					
					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
							.body(userData.get());
				}
				return new ResponseEntity<>("Incorrect password", HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>("No such a user", HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
	    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
	    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
	        .body("You've been signed out!");
	  }
}
