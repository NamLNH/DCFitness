package com.example.dcfitness;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dcfitness.model.AchievementRepository;
import com.example.dcfitness.model.Achievements;
import com.example.dcfitness.model.User;
import com.example.dcfitness.model.UserRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;
import com.example.dcfitness.model.Category;
import com.example.dcfitness.model.CategoryRepository;

@SpringBootApplication
public class DcFitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcFitnessApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepo,
			AchievementRepository achRepo,
			VideoRepository videoRepository,
			CategoryRepository categoryRepository) {
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
			
			//categories (name)
			categoryRepository.save(new Category("Cardio"));
			categoryRepository.save(new Category("Strength"));
			categoryRepository.save(new Category("Flexibility"));
			
			//video (title, url, thumbnail, uploadDate, author)
			videoRepository.save(new Video("20 MIN FULL BODY WORKOUT FOR BEGINNERS",
					"https://youtu.be/iCQ2gC4DqJw",
					"",
					"Feb 21 2024",
					"Billy Juice",
					new Category("Cardio")));
			videoRepository.save(new Video("10 MINUTE MORNING WORKOUT",
					"https://youtu.be/3sEeVJEXTfY?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm",
					"",
					"Feb 21 2024",
					"Billy Juice",
					new Category("Cardio")));
			videoRepository.save(new Video("5 MINUTE BEDTIME WORKOUT",
					"https://youtu.be/5Q4ewnHMNW8?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm",
					"",
					"Feb 21 2024",
					"Billy Juice",
					new Category("Cardio")));

            //achRepo.findAll().forEach(System.out::println);
			
			//userRepo.findAll().forEach(System.out::println);
		};
	}

}
