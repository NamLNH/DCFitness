package com.example.dcfitness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcfitness.model.AchievementRepository;
import com.example.dcfitness.model.Achievements;
import com.example.dcfitness.model.User;
@RestController
public class AchievementsController {
	@Autowired
	AchievementRepository achRepo;
	
	@GetMapping("/achievements")
	public ResponseEntity<List<Achievements>> getAllAchievements(
			@RequestParam(required = false) String title)
	{
		try
		{
			List<Achievements> ach = new ArrayList<Achievements>();
				achRepo.findAll().forEach(ach::add);
				System.out.println(ach.size());
				
				return new ResponseEntity<>(ach,HttpStatus.OK);
		} catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
//	@GetMapping("/achievements/{id}")
//	public ResponseEntity<User> getAchievementById(@PathVariable("id") long id){
//		List<Achievements> achievement = achRepo.findById(id);
//		if(achievement.size() > 0) {
//			return new ResponseEntity<>(achievement.get(0),HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
	
	@GetMapping("/achievements/{id}")
    public Achievements getAchievementById(@PathVariable Long id) {
		return achRepo.findById(id).orElse(null);
    }
    
	
}
