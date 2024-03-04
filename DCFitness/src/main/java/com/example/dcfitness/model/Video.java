package com.example.dcfitness.model;

import java.util.HashSet;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "videos")

public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
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
	
	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn (name ="category_id",nullable = false, referencedColumnName ="id")
	private Category category;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (
		name ="video_bodypart",
		joinColumns = @JoinColumn(name = "video_id", nullable = false, referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "bodypart_id", nullable = false, referencedColumnName ="id") 
	)
	private Set<BodyPart> bodyParts = new HashSet<>();
	
	public Video () {
		
	}
	public Video (String title, String url, String thumbnail, String uploadDate, String author, Category category) {
		this.title = title;
		this.url = url;
		this.thumbnail = thumbnail;
		this.uploadDate = uploadDate;
		this.author = author;
		this.category = category;
	}
	public Video (String title, String url, String thumbnail, String uploadDate, String author) {
		this.title = title;
		this.url = url;
		this.thumbnail = thumbnail;
		this.uploadDate = uploadDate;
		this.author = author;
		
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
	
	
}
