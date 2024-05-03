package com.social.media.project.service;

import java.io.IOException;
import java.util.List;

import com.social.media.project.entity.Story;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.StoryDto;

public interface StoryService{

	Story createStory(Story story,String username) throws CustomException, IOException;

	String deleteStory(int id) throws CustomException;

	List<StoryDto> getAllStoryOfFollowingUser(String username) throws CustomException;

	Story getStoryById(int id) throws CustomException;

}
