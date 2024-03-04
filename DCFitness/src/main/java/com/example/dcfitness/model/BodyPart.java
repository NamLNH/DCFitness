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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "body_part")

public class BodyPart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="name")
	private String name;	
	
	@JsonIgnore
	@ManyToMany(mappedBy ="bodyParts", fetch = FetchType.LAZY)
	private Set<Video> videos = new HashSet<>();
	
	public BodyPart () {		
	}
	public BodyPart (String name) {
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
	public Set<Video> getVideos() {
		return videos;
	}
	
}
