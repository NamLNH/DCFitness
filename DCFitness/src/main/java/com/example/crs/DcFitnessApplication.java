package com.example.crs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.crs.model.AchievementRepository;
import com.example.crs.model.Achievements;
import com.example.crs.model.User;
import com.example.crs.model.UserRepository;

@SpringBootApplication
public class DcFitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcFitnessApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepo, AchievementRepository achRepo) {
		return args -> {
			
			User yash = new User("YashManiar","BCOne","yashmr@student.douglascollege.ca");
			
			userRepo.save(new User("QuanHN","VancouverOne","quanhn@student.douglascollege.ca"));
			userRepo.save(new User("NamLe","BurnabyOne","namle@student.douglascollege.ca"));
			userRepo.save(new User("AungOT","CoquitlamOne","aungot@student.douglascollege.ca"));
			userRepo.save(new User("YashManiar","BCOne","yashmr@student.douglascollege.ca"));
			userRepo.save(new User("IvanWong","NewWestminsterOne","ivanw@douglascollege.ca"));
			userRepo.save(new User("Bambang","ScriptOne","bambang@douglascollege.ca"));
			userRepo.save(new User("EdwinVk","NedalandOne","edwink@douglascollege.ca"));
			userRepo.save(new User("Metzel","MetroOne","metrotown@douglascollege.ca"));
		
			achRepo.save(new Achievements(1, yash, "Sky-Peeing", "Description 1", "2023-01-01"));
			achRepo.save(new Achievements(2, yash, "Black Hole Destoyer", "Description 2", "2023-02-02"));
			achRepo.save(new Achievements(3, yash, "The Pianist", "Description 3", "2023-03-03"));

            achRepo.findAll().forEach(System.out::println);
			
			userRepo.findAll().forEach(System.out::println);
		};
	}

}
