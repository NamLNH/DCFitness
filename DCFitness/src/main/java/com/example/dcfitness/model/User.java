package com.example.dcfitness.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	
	@Column(name="role")
	private String role;
	
	//BOC by Yash 26-02-2024
	
	@OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
	private List<Achievements> achievements;
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(
		name = "User_Video",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn(name = "video_id") }
	)
	private Set<Video> favoriteVideos;

//    public void setFavoriteVideos(List<Video> favoriteVideos) {
//        this.favoriteVideos = new HashSet<Video>(favoriteVideos);
//    }
//
//    public void addFavoriteVideo(Video video) {
//    	this.favoriteVideos = new HashSet<Video>();
//        this.favoriteVideos.add(video);
//    }
//
//    public void removeFavoriteVideo(Video video) {
//        this.favoriteVideos.remove(video);
//    }
	public User() {}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = "user";
		this.achievements = new ArrayList<Achievements>();
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
	
	public void assignFavVideo(Video video) {
		this.favoriteVideos.add(video);
	}
	
	public Set<Video> getFavoriteVideos() {
        return this.favoriteVideos;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
