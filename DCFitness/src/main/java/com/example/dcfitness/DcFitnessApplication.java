package com.example.dcfitness;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dcfitness.model.AchievementRepository;
import com.example.dcfitness.model.Achievements;
import com.example.dcfitness.model.BodyPart;
import com.example.dcfitness.model.BodyPartRepository;
import com.example.dcfitness.model.User;
import com.example.dcfitness.model.UserRepository;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;
import com.example.dcfitness.model.Category;
import com.example.dcfitness.model.CategoryRepository;
import com.example.dcfitness.model.CommentRepository;
import com.example.dcfitness.model.Comment;

@SpringBootApplication
public class DcFitnessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcFitnessApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepo,
			AchievementRepository achRepo,
			VideoRepository videoRepository,
			CategoryRepository categoryRepository,
			BodyPartRepository bodyPartRepository,
			CommentRepository commentRepo) {
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
			
			//body parts (name)
			bodyPartRepository.save(new BodyPart("All body"));
			bodyPartRepository.save(new BodyPart("Upper body"));
			bodyPartRepository.save(new BodyPart("Lower body"));
			
			//new videos
			//String title, String url, String thumbnail, String uploadDate, String author
			Video video1 = initNewVideo(categoryRepository,Long.parseLong("1"),
					new Video("20 MIN FULL BODY WORKOUT FOR BEGINNERS",
					"https://youtu.be/iCQ2gC4DqJw",
					"",
					"3/3/2024",
					"Nam"));
			Video video2 = initNewVideo(categoryRepository,Long.parseLong("2"),
					new Video("10 MINUTE MORNING WORKOUT",
					"https://youtu.be/3sEeVJEXTfY?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm",
					"",
					"3/3/2024",
					"John"));
			//videoRepository.save(initNewVideo(categoryRepository,Long.parseLong("1"),video1));
			System.out.println(video1.getCategory().getName());
			System.out.println(video1.getAuthor());
			try {
				videoRepository.save(video1);
				videoRepository.save(video2);
			} catch (Exception e) {
				System.out.println("error in saving to database");
				System.out.println(e.getMessage());
			}
			
			// new comments
			Comment comment1 = initComments(videoRepository, userRepo, Long.parseLong("1"), Long.parseLong("1"),
					new Comment("This is the best workout video!!!"));
			Comment comment2 = initComments(videoRepository, userRepo, Long.parseLong("1"), Long.parseLong("2"),
					new Comment("Thank you for your content"));
			Comment comment3 = initComments(videoRepository, userRepo, Long.parseLong("1"), Long.parseLong("1"),
					new Comment("Yes, motivated!!!"));
			Comment comment4 = initComments(videoRepository, userRepo, Long.parseLong("2"), Long.parseLong("3"),
					new Comment("Will you do more of the upper body workout videos?"));
			Comment comment5 = initComments(videoRepository, userRepo, Long.parseLong("2"), Long.parseLong("4"),
					new Comment("...???"));
			
			try {
				commentRepo.save(comment1);
				commentRepo.save(comment2);
				commentRepo.save(comment3);
				commentRepo.save(comment4);
				commentRepo.save(comment5);
			} catch (Exception e) {
				System.out.println("error in saving comment to database");
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
	
	public static Comment initComments(VideoRepository videoRepository, UserRepository userRepository, Long videoId, Long userId, Comment comment) {
		try {
			Optional<Video> _video = videoRepository.findById(videoId);
			Optional<User> _user = userRepository.findById(userId);
			
			
			if(_video.isPresent())
			{
				comment.setVideo(_video.get());
			} else {
				System.out.println("Cant find the video in list");
			}
			
			if(_user.isPresent())
			{
				comment.setUser(_user.get());
			} else {
				System.out.println("Cant find the user in list");
			}
		} catch (Exception e) {
			System.out.println("Error in getting video and user from database");
		}
			
		return comment;
	}
}
