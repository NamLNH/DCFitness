package com.example.dcfitness.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@CreationTimestamp
	@Column(name="createdOn")
    private Instant createdOn;
	
    @UpdateTimestamp
	@Column(name="lastUpdatedOn")
    private Instant lastUpdatedOn;
	
	@Column(name="content")
	private String content;

	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn (name ="video_id",nullable = false, referencedColumnName ="id")
	private Video video;
	
	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn (name ="user_id",nullable = false, referencedColumnName ="id")
	private User user;
	
	public Comment () {}
	
	public Comment (String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public Video getVideo() {
		return video;
	}

	public User getUser() {
		return user;
	}

	public String getContent() {
		return content;
	}
	
	public Instant getCreatedOn() {
		return createdOn;
	}
	
	public Instant getlastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}
	
	public void setlastUpdatedOn(Instant lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
