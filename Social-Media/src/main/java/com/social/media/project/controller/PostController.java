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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.entity.Post;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.PostDto;
import com.social.media.project.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping
	public ResponseEntity<Post> createPost(@ModelAttribute Post post) throws CustomException, IOException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Post postCreated = postService.createPost(post,username);
		return ResponseEntity.status(HttpStatus.CREATED).body(postCreated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPost(@PathVariable int id) throws CustomException{
		Post post = postService.getPostById(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> getAllPostOfUser() throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PostDto> allPost = postService.getAllPostOfUser(username);
		
		return ResponseEntity.status(HttpStatus.OK).body(allPost);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@ModelAttribute Post post,@PathVariable int id) throws CustomException{
		Post updatePost = postService.updatePost(id,post);
		return ResponseEntity.status(HttpStatus.OK).body(updatePost);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable int id) throws CustomException{
	    String message = postService.deletePost(id);
	    return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	@GetMapping("/all-follow-post")
	public ResponseEntity<List<PostDto>> getAllpostOfFollowing() throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PostDto> posts = postService.getAllPostOfFollwing(username);
	    return ResponseEntity.status(HttpStatus.OK).body(posts);
	}
	
	@GetMapping("/post-cnt")
	public ResponseEntity<Integer> getAllPostCountOfUser() throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		int cnt = postService.getPostCntOfUser(username);
		return ResponseEntity.status(HttpStatus.OK).body(cnt);
	}
}









