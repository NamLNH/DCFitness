package com.example.crs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.crs.model.User;
import com.example.crs.model.UserRepository;

@SpringBootApplication
public class DcFitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcFitnessApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepo) {
		return args -> {
			userRepo.save(new User("QuanHN","VancouverOne","quanhn@student.douglascollege.ca"));
			userRepo.save(new User("NamLe","BurnabyOne","namle@student.douglascollege.ca"));
			userRepo.save(new User("AungOT","CoquitlamOne","aungot@student.douglascollege.ca"));
			userRepo.save(new User("YashManiar","SurreyOne","yashmr@student.douglascollege.ca"));
			userRepo.save(new User("IvanWong","NewWestminsterOne","ivanw@douglascollege.ca"));
			userRepo.save(new User("Bambang","ScriptOne","bambang@douglascollege.ca"));
			userRepo.save(new User("EdwinVk","NedalandOne","edwink@douglascollege.ca"));
			userRepo.save(new User("Metzel","MetroOne","metrotown@douglascollege.ca"));
		
			userRepo.findAll().forEach(System.out::println);
		};
	}

}
