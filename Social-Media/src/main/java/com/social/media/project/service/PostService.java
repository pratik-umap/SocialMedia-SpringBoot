package com.social.media.project.service;

import java.io.IOException;
import java.util.List;

import com.social.media.project.entity.Post;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.PostDto;

public interface PostService {

	Post createPost(Post post,String username) throws CustomException, IOException;

	Post getPostById(int id) throws CustomException;

	Post updatePost(int id, Post post) throws CustomException;

	String deletePost(int id) throws CustomException;

	List<PostDto> getAllPostOfUser(String username) throws CustomException;
	
	List<PostDto> getAllPostOfFollwing(String username) throws CustomException;

	int getPostCntOfUser(String username) throws CustomException;

}
