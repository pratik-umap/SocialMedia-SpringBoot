package com.social.media.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.entity.Comment;
import com.social.media.project.exception.CustomException;
import com.social.media.project.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment,@RequestParam int postId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Comment save = commentService.createComment(comment,username,postId);
		
		return ResponseEntity.status(HttpStatus.OK).body(save);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<Comment>> getAllCommentOfPost(@PathVariable int postId) throws CustomException{
		List<Comment> allComment = commentService.getAllCommentOfPost(postId);
		
		return ResponseEntity.status(HttpStatus.OK).body(allComment);
	}
	
	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment,@PathVariable int commentId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Comment comment1 = commentService.updateComment(comment,username,commentId);
		return ResponseEntity.status(HttpStatus.OK).body(comment1);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deletComment(@PathVariable int commentId) throws CustomException{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		String message = commentService.deleteComment(username,commentId);
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
} 
