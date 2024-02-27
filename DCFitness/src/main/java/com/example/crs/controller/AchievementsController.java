package com.example.crs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crs.model.AchievementRepository;
import com.example.crs.model.Achievements;
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
	
}
