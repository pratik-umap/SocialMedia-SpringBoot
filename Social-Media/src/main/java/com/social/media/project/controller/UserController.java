package com.social.media.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.UserDto;
import com.social.media.project.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		UserEntity userCreated = userService.createUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> allUser = userService.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(allUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable int id) throws CustomException{
		UserEntity user = userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user,@PathVariable int id) throws CustomException{
		UserEntity user1 = userService.updateUser(id,user);
		return ResponseEntity.status(HttpStatus.OK).body(user1);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) throws CustomException{
	 String message = userService.deleteUser(id);
	 return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserDto>> searchUser(@RequestParam String search){
		List<UserDto> list= userService.searchUser(search);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
}
