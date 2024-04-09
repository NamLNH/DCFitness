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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "categories")

public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="name")
	private String name;	
	
	@JsonIgnore
	@OneToMany (mappedBy ="category", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Video> videos = new HashSet<>();
	
	public Category () {		
	}
	public Category (String name) {
		this.name = name;		
	}	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Set<Video> getVideos() {
//		return videos;
//	}
//	public void setVideos(Set<Video> videos) {
//		this.videos = videos;
//	}
	
	
}
