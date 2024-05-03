package com.social.media.project.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.project.entity.Like;
import com.social.media.project.entity.Post;
import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.repository.LikeRepository;
import com.social.media.project.repository.PostRepository;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public String likePost(String username, int postId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("post not found"));

		Like like= new Like();
		like.setUser(user.getId());
		like.setPost(postId);
		like.setCreatedAt(new Date());
		Like save = likeRepository.save(like);
		return "post like successfully";
	}

	@Override
	public String unLikePost(String username, int postId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("post not found"));

		Like findByUserAndPost = likeRepository.findByUserAndPost(user.getId(), postId);
		likeRepository.delete(findByUserAndPost);
		return "post unlike successfully";
	}

}
