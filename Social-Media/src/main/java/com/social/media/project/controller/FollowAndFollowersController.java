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
import com.social.media.project.service.FollowAndFollowersService;

@RestController
@RequestMapping("/api")
public class FollowAndFollowersController {

	@Autowired
	private FollowAndFollowersService followAndFollowersService;
	
	@GetMapping("/follow/{followUserId}")
	public ResponseEntity<String> followUser(@PathVariable int followUserId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); 
		String message = followAndFollowersService.followUser(username,followUserId);
		 return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/unfollow/{followUserId}")
	public ResponseEntity<String> unFollowUser(@PathVariable int followUserId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); 
		String message = followAndFollowersService.unFollowUser(username,followUserId);
		 return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/user/following-cnt")
	public ResponseEntity<Integer> getFollowingCntOfUser() throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int cnt = followAndFollowersService.getFollowingCntOfUser(username);
		 return ResponseEntity.status(HttpStatus.OK).body(cnt);
	}
	
	@GetMapping("/user/followers-cnt")
	public ResponseEntity<Integer> getFollowersCntOfUser() throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int cnt = followAndFollowersService.getFollowersCntOfUser(username);
		 return ResponseEntity.status(HttpStatus.OK).body(cnt);
	}
}



