package com.example.dcfitness.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "categories")

public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="name")
	private String name;
	@Column(name ="description")
	private String description;
	@Column(name ="difficultLevel")
	private String difficultLevel;
	
	
	public Category () {
		
	}
	public Category (String name, String description, String difficultLevel) {
		this.name = name;
		this.description = description;
		this.difficultLevel = difficultLevel;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDifficultLevel() {
		return difficultLevel;
	}
	public void setDifficultLevel(String difficultLevel) {
		this.difficultLevel = difficultLevel;
	}
	public long getId() {
		return id;
	}
	
}
