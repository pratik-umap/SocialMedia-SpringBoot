package com.social.media.project.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "t_user_post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int id;
	
	@Column(name = "post_desc")
	private String description;
	
	@Column(name = "post_location")
	private String location;
	
	transient private MultipartFile image;
	
	@Column(name = "post_image")
	private String image_path;

	@Column(name = "createdAt")
	private Date createdAt;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_user_id")
	private UserEntity user;
	
	@OneToMany(mappedBy = "post")
	private List<Comment> comment;
	
	transient private String genaratedPresignedUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getGenaratedPresignedUrl() {
		return genaratedPresignedUrl;
	}

	public void setGenaratedPresignedUrl(String genaratedPresignedUrl) {
		this.genaratedPresignedUrl = genaratedPresignedUrl;
	}
	
	
}
