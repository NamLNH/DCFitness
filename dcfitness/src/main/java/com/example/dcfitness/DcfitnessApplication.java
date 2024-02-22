package com.example.dcfitness;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dcfitness.model.Category;
import com.example.dcfitness.model.CategoryRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@SpringBootApplication
public class DcfitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcfitnessApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init (VideoRepository videoRepo, CategoryRepository categoryRepo) {
		return args -> {
			//video (title, url, thumbnail, uploadDate, author)
			videoRepo.save(new Video("20 MIN FULL BODY WORKOUT FOR BEGINNERS",
					"https://youtu.be/iCQ2gC4DqJw",
					"",
					"Feb 21 2024",
					"Billy Juice"));
			videoRepo.save(new Video("10 MINUTE MORNING WORKOUT",
					"https://youtu.be/3sEeVJEXTfY?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm",
					"",
					"Feb 21 2024",
					"Billy Juice"));
			videoRepo.save(new Video("5 MINUTE BEDTIME WORKOUT",
					"https://youtu.be/5Q4ewnHMNW8?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm",
					"",
					"Feb 21 2024",
					"Billy Juice"));
			
			//exercise (name, description, difficult level)
			categoryRepo.save(new Category("Cardio",
					"",
					""));
		};
		
	}

}
