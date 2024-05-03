package com.social.media.project.entity;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "t_user_story")
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fk_user_id")
	private int user;
	
	transient private MultipartFile file;
	
	@Column(name = "image_path")
	private String image_path;
	
	@Column(name = "createdAt")
	private Date createdAt;

	transient private String genaratedPreSignedUrl;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getGenaratedPreSignedUrl() {
		return genaratedPreSignedUrl;
	}

	public void setGenaratedPreSignedUrl(String genaratedPreSignedUrl) {
		this.genaratedPreSignedUrl = genaratedPreSignedUrl;
	}
	
	
	
}
