package com.example.dcfitness;

import java.util.ArrayList;
import java.util.Optional;

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
			//new videos
			//String title, String url, String thumbnail, String uploadDate, String author
			Video video1 = initNewVideo(categoryRepository,Long.parseLong("1"),
					new Video("20 MIN FULL BODY WORKOUT FOR BEGINNERS",
					"https://youtu.be/iCQ2gC4DqJw",
					"",
					"3/3/2024",
					"Nam"));
			//videoRepository.save(initNewVideo(categoryRepository,Long.parseLong("1"),video1));
			System.out.println(video1.getCategory().getName());
			System.out.println(video1.getAuthor());
			try {
				videoRepository.save(video1);
			} catch (Exception e) {
				System.out.println("error in saving to database");
				System.out.println(e.getMessage());
			}
		};
	}
	public static Video initNewVideo(CategoryRepository categoryRepo, Long categoryId, Video video) {
		try {
			Optional<Category> _category = categoryRepo.findById(categoryId);
			
			if(_category.isPresent())
			{
				video.setCategory(_category.get());
			}
			else {
				System.out.println("Cant find the category in list");
			}
		} catch (Exception e) {
			System.out.println("Error in getting category from database");
		}
			
		return video;
	}
}
