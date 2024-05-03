package com.social.media.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.exception.CustomException;
import com.social.media.project.service.LikeService;

@RestController
@RequestMapping("/api")
public class LikeController {
    
	@Autowired 
	private LikeService likeService;
	
	@GetMapping("/like/{postId}")
	public ResponseEntity<String> likePost(@PathVariable int postId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String message = likeService.likePost(username,postId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/unlike/{postId}")
	public ResponseEntity<String> unLikePost(@PathVariable int postId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		String message = likeService.unLikePost(username,postId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
}
