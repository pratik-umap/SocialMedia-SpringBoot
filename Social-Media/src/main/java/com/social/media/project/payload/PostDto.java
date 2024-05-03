package com.social.media.project.payload;

import java.util.List;

public class PostDto {

	private int id;
	
	private String location;
	
	private String image_path;
	
	private String description;
	
	private List<UserDto> likes;
	
	private List<CommentDto> comments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserDto> getLikes() {
		return likes;
	}

	public void setLikes(List<UserDto> likes) {
		this.likes = likes;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	
	
}
