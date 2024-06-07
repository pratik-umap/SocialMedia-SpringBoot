package com.social.media.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.FollowAndFollowersService;

@RestController
@RequestMapping("/api")
public class FollowAndFollowersController {

	@Autowired
	private FollowAndFollowersService followAndFollowersService;
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	@GetMapping("/follow-user")
	public ResponseEntity<Boolean> followUserExist(@RequestParam int id){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean val = followAndFollowersService.followUserExist(name,id);
		return ResponseEntity.status(HttpStatus.OK).body(val);
	}
	
	@GetMapping("/user/following-cnt")
	public ResponseEntity<Integer> getFollowingCntOfUser(@RequestParam(defaultValue = "0") int id) throws CustomException{
		UserEntity user =  null;
		if (id!=0) {
			 user = userRepository.findById(id).get();
		} else {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			 user = userRepository.findByUsername(username);
		}
		int cnt = followAndFollowersService.getFollowingCntOfUser(user);
		 return ResponseEntity.status(HttpStatus.OK).body(cnt);
	}
	
	@GetMapping("/user/followers-cnt")
	public ResponseEntity<Integer> getFollowersCntOfUser(@RequestParam(defaultValue = "0") int id) throws CustomException{
		UserEntity user = null;
		if (id != 0) {
			user = userRepository.findById(id).get();
		} else {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			user = userRepository.findByUsername(username);
		}
		int cnt = followAndFollowersService.getFollowersCntOfUser(user);
		 return ResponseEntity.status(HttpStatus.OK).body(cnt);
	}
}



