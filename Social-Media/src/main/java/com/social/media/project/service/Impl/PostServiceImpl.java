package com.social.media.project.service.Impl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import com.amazonaws.services.s3.model.PutObjectResult;
import com.social.media.project.entity.Comment;
import com.social.media.project.entity.Like;
import com.social.media.project.entity.Post;
import com.social.media.project.entity.PostPojo;
import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.CommentDto;
import com.social.media.project.payload.PostDto;
import com.social.media.project.payload.UserDto;
import com.social.media.project.repository.LikeRepository;
import com.social.media.project.repository.PostRepository;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.CommentService;
import com.social.media.project.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${aws.bucket.name}")
	private String bucketName;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	LikeRepository likeRepository;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public Post createPost(Post post,String username) throws CustomException, IOException {
		UserEntity user = userRepository.findByUsername(username);
	
		post.setCreatedAt(new Date());
		post.setUser(user);
		//s3 imaplementation
		String key = UUID.randomUUID().toString();
		ObjectMetadata metadata= new ObjectMetadata();
		metadata.setContentType("image/jpeg");
		PutObjectResult putObject = amazonS3.putObject(bucketName, key, post.getImage().getInputStream(), metadata);
//		String copyImage = FileUtils.copyImage(post.getImage()); used for save file in local
		post.setImage_path(key);
		
		Post save = postRepository.save(post);
		save.setImage(null);
		return save;
	}

	@Override
	public Post getPostById(int id) throws CustomException {
		Post post = postRepository.findById(id).orElseThrow(()-> new CustomException("post not found"));
		 Date date = new Date();
			long exp = date.getTime() + 2 * 60 * 60 * 1000;
			date.setTime(exp);
		
	    	URL generatePresignedUrl = amazonS3.generatePresignedUrl(bucketName, post.getImage_path(),date, HttpMethod.GET);
	      post.setGenaratedPresignedUrl(generatePresignedUrl.toString());
		return post;
	}

	@Override
	public Post updatePost(int id, Post post) throws CustomException {
		Post postupdate = postRepository.findById(id).orElseThrow(()-> new CustomException("post not found"));
		if (post.getLocation() != null) {
			postupdate.setLocation(post.getLocation());
		}
		if (post.getDescription() != null) {
			postupdate.setDescription(post.getDescription());
		}
		// s3 implementation
		Post save = postRepository.save(postupdate);
		return save;
	}

	@Override
	public String deletePost(int id) throws CustomException {
		Post post = postRepository.findById(id).orElseThrow(()-> new CustomException("post not found"));
		amazonS3.deleteObject(bucketName, post.getImage_path());
		postRepository.delete(post);
		return "post deleted successfully";
	}

	@Override
	public List<PostDto> getAllPostOfUser(String username) throws CustomException {
	UserEntity user = userRepository.findByUsername(username);
	
      List<Post> postpojo = postRepository.findByUser(user);
		List<PostDto> posts= new ArrayList<PostDto>();

        Date date = new Date();
		long exp = date.getTime() + 2 * 60 * 60 * 1000;
		date.setTime(exp);
		
		for (int i = 0; i < postpojo.size(); i++) {
			PostDto post= new PostDto();
			post.setId(postpojo.get(i).getId());
	    	URL generatePresignedUrl = amazonS3.generatePresignedUrl(bucketName, postpojo.get(i).getImage_path(),date, HttpMethod.GET);
			post.setImage_path(generatePresignedUrl.toString());
			post.setLocation(postpojo.get(i).getLocation());
			post.setDescription(postpojo.get(i).getDescription());
			posts.add(post);
		}
		
		return posts;
	}
	
	public List<PostDto> getAllPostOfFollwing(String username) throws CustomException{
		UserEntity userentity = userRepository.findByUsername(username);
		List<PostPojo> postpojo = postRepository.findPostsByUser(userentity.getId());
		List<PostDto> posts= new ArrayList<PostDto>();
		 Date date = new Date();
		 long exp = date.getTime() + 2 * 60 * 60 * 1000;
		 date.setTime(exp);
		 
		for (int i = 0; i < postpojo.size(); i++) {
			PostDto post= new PostDto();
			post.setId(postpojo.get(i).getPostId().intValue());
	    	URL generatePresignedUrl = amazonS3.generatePresignedUrl(bucketName, postpojo.get(i).getPostImage(),date, HttpMethod.GET);
			post.setImage_path(generatePresignedUrl.toString());
			post.setLocation(postpojo.get(i).getPostLocation());
			post.setDescription(postpojo.get(i).getPostDesc());
			
			List<Like> likes = likeRepository.findByPost(post.getId());
			List<UserDto> collect = likes.stream().map((like)-> {
				UserEntity userEntity = userRepository.findById(like.getUser()).get();
				UserDto user = mapper.map(userEntity, UserDto.class);
				return user;
			}).collect(Collectors.toList());
			post.setLikes(collect);
			
			List<Comment> allCommentOfPost = commentService.getAllCommentOfPost(post.getId());
			List<CommentDto> collect2 = allCommentOfPost.stream().map((comment)->{
				CommentDto commentDto = new CommentDto();
				commentDto.setId(comment.getId());
				commentDto.setDescription(comment.getDescription());
				UserDto user = mapper.map(comment.getUser(), UserDto.class);
				commentDto.setUser(user);
				return commentDto;
			}).collect(Collectors.toList());
			post.setComments(collect2);
			posts.add(post);
		}
	
		return posts;
	}

	@Override
	public int getPostCntOfUser(String username) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);

		int findPostByUser = postRepository.findPostByUser(user.getId());
		return findPostByUser;
	}
	

}
