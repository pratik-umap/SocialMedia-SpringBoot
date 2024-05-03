package com.social.media.project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.entity.Story;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.StoryDto;
import com.social.media.project.service.StoryService;


@RestController
@RequestMapping("/api/story")
public class StoryController {

	@Autowired 
	private StoryService storyService;
	
    @PostMapping
    public ResponseEntity<Story> createStory(@ModelAttribute Story story) throws CustomException, IOException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); 
    	Story story1 = storyService.createStory(story,username);
    	 return ResponseEntity.status(HttpStatus.OK).body(story1);
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable int id) throws CustomException{
    	Story story = storyService.getStoryById(id);
    	return ResponseEntity.status(HttpStatus.OK).body(story);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStory(@PathVariable int id) throws CustomException{
    	String message = storyService.deleteStory(id);
    	return ResponseEntity.status(HttpStatus.OK).body(message);
    }
    
    @GetMapping("/following-user")
    public ResponseEntity<List<StoryDto>> getAllStoryOfFollowingUser() throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	List<StoryDto> allStory = storyService.getAllStoryOfFollowingUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(allStory);
    }
}  

