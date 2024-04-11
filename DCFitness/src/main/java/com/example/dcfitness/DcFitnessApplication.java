package com.example.dcfitness;


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
import com.example.dcfitness.model.Challenge;
import com.example.dcfitness.model.ChallengeRepository;
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
			CommentRepository commentRepo,
			ChallengeRepository challengeRepo) {
		return args -> {
			
			User yash = new User("YashManiar","BCOne","yashmr@student.douglascollege.ca");
			
			userRepo.save(new User("QuanHN","VancouverOne","quanhn@student.douglascollege.ca"));
			userRepo.save(new User("nam","123456","namle@student.douglascollege.ca"));
			userRepo.save(new User("AungOT","CoquitlamOne","aungot@student.douglascollege.ca"));
			userRepo.save(new User("YashManiar","BCOne","yashmr@student.douglascollege.ca"));
			userRepo.save(new User("IvanWong","NewWestminsterOne","ivanw@douglascollege.ca"));
			userRepo.save(new User("Bambang","ScriptOne","bambang@douglascollege.ca"));
			userRepo.save(new User("EdwinVk","NedalandOne","edwink@douglascollege.ca"));
			userRepo.save(new User("Metzel","MetroOne","metrotown@douglascollege.ca"));
			
			//admin
			User admin = new User("admin","admin","admin@gmail.com");
			admin.setRole("admin");
			userRepo.save(admin);
		
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
					"Nam"));
			Video video2 = initNewVideo(categoryRepository,Long.parseLong("2"),
					new Video("10 MINUTE MORNING WORKOUT",
					"https://youtu.be/3sEeVJEXTfY?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm",					
					"John"));
			Video video3 = initNewVideo(categoryRepository,Long.parseLong("1"),
					new Video("15 MIN BEGINNER CARDIO Workout (At Home No Equipment)",
					"https://youtu.be/VWj8ZxCxrYk?si=jejGELkkI2QnXmcZ",					
					"MadFit"));
			Video video4 = initNewVideo(categoryRepository,Long.parseLong("1"),
					new Video("15 Minute Full Body Cardio Workout (No Equipment)",
					"https://youtu.be/IvmaekQfKiw?si=19D8xYVLIJL7y3SX",					
					"Juice & Toya"));
			Video video5 = initNewVideo(categoryRepository,Long.parseLong("2"),
					new Video("6 easy strength training exercises",
					"https://youtu.be/H1F-UfC8Mb8?si=N6lC6exDfERgFAOS",					
					"Leo"));
			Video video6 = initNewVideo(categoryRepository,Long.parseLong("2"),
					new Video("Full Body Strength Workout With Dumbbells",
					"https://youtu.be/sinkIlViPG8?si=Nu75c3qDDImb3EeY",					
					"Heather Robertson"));
			Video video7 = initNewVideo(categoryRepository,Long.parseLong("3"),
					new Video("8 Minute Stretching Routine For People Who AREN’T Flexible!",
					"https://youtu.be/FI51zRzgIe4?si=MBhmx7PYe5UMS9yL",					
					"Tone & Tighten"));
			Video video8 = initNewVideo(categoryRepository,Long.parseLong("3"),
					new Video("15 MIN FULL BODY STRETCH - Improve Mobility and Flexibility",
					"https://youtu.be/i6TzP2COtow?si=tfhkGav4dQQa0aSF",					
					"growingannanas"));
			Video video9 = initNewVideo(categoryRepository,Long.parseLong("3"),
					new Video("Full Body Yoga for Strength & Flexibility | 25 Minute At Home Mobility Routine",
					"https://youtu.be/Eml2xnoLpYE?si=LzcdTWVLMEg6JqaC",					
					"growingannanas"));
			//https://youtu.be/iCQ2gC4DqJw
			//https://youtu.be/3sEeVJEXTfY?list=PLjngT6yRRZHPsjj-CIduSL9FpclppM9qm
			
			//videoRepository.save(initNewVideo(categoryRepository,Long.parseLong("1"),video1));
//			System.out.println(video1.getCategory().getName());
//			System.out.println(video1.getAuthor());
			try {
				videoRepository.save(video1);
				videoRepository.save(video2);
				videoRepository.save(video3);
				videoRepository.save(video4);
				videoRepository.save(video5);
				videoRepository.save(video6);
				videoRepository.save(video7);
				videoRepository.save(video8);
				videoRepository.save(video9);
			} catch (Exception e) {
				System.out.println("error in saving to database");
				System.out.println(e.getMessage());
			}
			
			// new comments - Aung
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
			
			// new challenges and users assigned to it - Aung
			Challenge challenge1 = new Challenge("2024 Challenge", "New Year, New Goals!!! Join this challenge to unlock your potential!!!");
			Challenge challenge2 = new Challenge("Mr Beast!", "There will be different games for different levels across the continent! Last day to submit your application is End of April.");
			Challenge challenge3 = new Challenge("Begineers", "Welcome all!");
			
			try {
				challengeRepo.save(challenge1);
				challengeRepo.save(challenge2);
				challengeRepo.save(challenge3);
				
				initUsersInChallenges(userRepo, Long.parseLong("1"), challenge1 ); // adding user 1 to challenge 1
				initUsersInChallenges(userRepo, Long.parseLong("1"), challenge2 );
				initUsersInChallenges(userRepo, Long.parseLong("1"), challenge3 );
				initUsersInChallenges(userRepo, Long.parseLong("2"), challenge1 );
				initUsersInChallenges(userRepo, Long.parseLong("2"), challenge2 );
				initUsersInChallenges(userRepo, Long.parseLong("3"), challenge3 );
				
				
			} catch (Exception e) {
				System.out.println("error in saving challenges to database");
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
	
	public static Challenge initUsersInChallenges(UserRepository userRepository, Long userId, Challenge challenge) {
		try {
			Optional<User> _user = userRepository.findById(userId);
			
			if(_user.isPresent())
			{
				challenge.assignUser(_user.get());
			} else {
				System.out.println("Cant find the user in list");
			}
		} catch (Exception e) {
			System.out.println("Error in getting video and user from database");
		}
			
		return challenge;
	}
}
