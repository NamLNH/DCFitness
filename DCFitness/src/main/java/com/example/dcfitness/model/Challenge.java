package com.example.dcfitness.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "challenges")
public class Challenge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@CreationTimestamp
	@Column(name="createdOn")
    private Instant createdOn;
	
    @UpdateTimestamp
	@Column(name="lastUpdatedOn")
    private Instant lastUpdatedOn;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (
		name ="challenge_user",
		joinColumns = @JoinColumn(name = "challenge_id", nullable = false, referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName ="id") 
	)
	private Set<User> users = new HashSet<>();
	
	
	public Challenge () {}
	
	public Challenge (String title, String description) {
		this.title = title;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Instant lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void assignUser(User user) {
		this.users.add(user);
	}
}
