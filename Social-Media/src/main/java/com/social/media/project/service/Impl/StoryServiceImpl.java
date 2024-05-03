package com.social.media.project.service.Impl;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.social.media.project.entity.Story;
import com.social.media.project.entity.StoryPojo;
import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.StoryDto;
import com.social.media.project.payload.UserDto;
import com.social.media.project.repository.StoryRepository;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${aws.bucket.name}")
	private String bucketName;
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	ModelMapper mapper;
	
	@Override
	public Story createStory(Story story,String username) throws CustomException, IOException {
		UserEntity user = userRepository.findByUsername(username);
		story.setCreatedAt(new Date());
		story.setUser(user.getId());
		// s3 implementation
		String key= UUID.randomUUID().toString();
		ObjectMetadata metadata=new ObjectMetadata();
		metadata.setContentType("image/jpeg");
		amazonS3.putObject(bucketName, key, story.getFile().getInputStream(), metadata);
		story.setImage_path(key);
//		String copyImage = FileUtils.copyImage(story.getFile());  used for save file in local
		
		Story save = storyRepository.save(story);
		save.setFile(null);
		return save;
	}

	@Override
	public String deleteStory(int id) throws CustomException {
		Story story = storyRepository.findById(id).orElseThrow(()-> new CustomException("story not found"));
		amazonS3.deleteObject(bucketName, story.getImage_path());
		storyRepository.delete(story);
		return "story deleted successfully";
	}

	@Override
	public List<StoryDto> getAllStoryOfFollowingUser(String username) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		Date date= new Date();
		long exp = date.getTime() + 24 * 60 * 60 * 1000;
		date.setTime(exp);
		
		List<StoryPojo> findStoryByUser = storyRepository.findStoryByUser(user.getId());
		List<StoryDto> collect = findStoryByUser.stream().map((story)->{
			StoryDto s = new StoryDto();
			s.setId(story.getId());
			
			URL generatePresignedUrl = amazonS3.generatePresignedUrl(bucketName, story.getImagePath(), date, HttpMethod.GET);
			s.setImage(generatePresignedUrl.toString());
			 UserEntity userEntity = userRepository.findById(story.getUser().intValue()).get();
			 UserDto map = mapper.map(userEntity, UserDto.class);
			s.setUser(map);
			return s;
		}).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public Story getStoryById(int id) throws CustomException {
		Story story = storyRepository.findById(id).orElseThrow(()-> new CustomException("story not found"));
		Date date= new Date();
		long exp = date.getTime() + 2 * 60 * 60 * 1000;
		date.setTime(exp);
		
		URL generatePresignedUrl = amazonS3.generatePresignedUrl(bucketName, story.getImage_path(), date, HttpMethod.GET);
		story.setGenaratedPreSignedUrl(generatePresignedUrl.toString());
		return story;
	}
	
	

}
