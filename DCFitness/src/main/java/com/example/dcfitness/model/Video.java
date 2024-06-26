package com.example.dcfitness.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "videos")

public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="UserProfile_id")
	private User user;
	
	@Column(name ="title")
	private String title;
	@Column(name ="url")
	private String url;
	@Column(name ="thumbnail")
	private String thumbnail;
	@Column(name ="uploadDate")
	private String uploadDate;
	@Column(name ="author")
	private String author;
	@Column(name ="youtube_id")
	private String videoId;
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn (name ="category_id", referencedColumnName ="id")
	private Category category;
	
	@JsonIgnore
	@OneToMany (mappedBy ="video", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Comment> comments = new HashSet<>();

	
	@JsonIgnore
	@ManyToMany(mappedBy ="favoriteVideos", fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	private Set<User> users = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinTable (
		name ="video_bodypart",
		joinColumns = @JoinColumn(name = "video_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "bodypart_id", referencedColumnName ="id") 
	)
	private Set<BodyPart> bodyParts = new HashSet<>();
	
	public Video () {
		
	}
	public Video (String title, String url, String uploadDate, String author, Category category) {
		this.title = title;
		this.url = url;
		this.thumbnail ="https://i.ytimg.com/vi/"+url.substring(17, 28)+"/default.jpg";
		//this.thumbnail = "https://i.ytimg.com/vi/"+url+"/mq3.jpg";
		this.uploadDate = uploadDate;
		this.author = author;
		this.category = category;
		this.videoId = url.substring(17, 28);
	}
	
	public Video (String title, String url, String author) {
		this.title = title;
		this.url = url;
		this.thumbnail = "https://i.ytimg.com/vi/"+url.substring(17, 28)+"/default.jpg";
		//this.thumbnail = this.thumbnail = "https://i.ytimg.com/vi/"+url+"/default.jpg";
		this.author = author;
		this.videoId = url.substring(17, 28);
	}
	
	public Video(String title, String url, String thumbnail, String author, String videoId) {
		this.title = title;
		this.url = url;
		this.thumbnail = thumbnail;
		this.author = author;
		this.videoId = videoId;
	}
	public long getId() {
		return id;
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<BodyPart> getBodyParts() {
		return bodyParts;
	}
	
	public void assignBodyPart(BodyPart part) {
		this.bodyParts.add(part);
	}
	
	public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = new HashSet<User>(users);
    }
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
    
	
}
