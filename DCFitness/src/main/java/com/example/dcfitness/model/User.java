package com.example.dcfitness.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserProfile")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	//BOC by Yash 26-02-2024
	
	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
	private List<Achievements> achievements;
	
	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
	private List<Video> favs;
	

//	 @ManyToMany(cascade = CascadeType.ALL)
//	    private List<Video> favoriteVideos = new ArrayList<>();
	 
//	public List<Video> getFavoriteVideos() {
//        return favoriteVideos;
//    }
//
//    public void setFavoriteVideos(List<Video> favoriteVideos) {
//        this.favoriteVideos = favoriteVideos;
//    }
//
//    public void addFavoriteVideo(Video video) {
//        favoriteVideos.add(video);
//    }
//
//    public void removeFavoriteVideo(Video video) {
//        favoriteVideos.remove(video);
//    }
	public User() {
	
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.achievements = new ArrayList<Achievements>();
		this.favs = new ArrayList<Video>();
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Video> getFavs() {
		return favs;
	}

	public void setFavs(Video favs) {
		this.favs.add(favs);
	}
	
	
}
