package com.social.media.project.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "t_user_master")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_mobileno")
	private String mobileno;
	
	@Column(name = "user_full_name")
	private String name;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@OneToMany(mappedBy = "user")
	private List<Post> post; 
	
	@OneToMany(mappedBy = "user")
	private List<Comment> comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public UserEntity(int id, String username, String password, String email, String mobileno, String name, Date createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobileno = mobileno;
		this.name = name;
		this.createdAt = createdAt;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
