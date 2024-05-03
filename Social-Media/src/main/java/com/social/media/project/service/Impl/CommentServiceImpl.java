package com.social.media.project.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.project.entity.Comment;
import com.social.media.project.entity.Post;
import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.repository.CommentRepository;
import com.social.media.project.repository.PostRepository;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment createComment(Comment comment,String username,int postId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("post not found"));
		
		comment.setCreatedAt(new Date());
		comment.setUser(user);
		comment.setPost(post);
		Comment save = commentRepository.save(comment);
		return save;
	}

	@Override
	public List<Comment> getAllCommentOfPost(int postId) throws CustomException {
		Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException("post not found"));

		List<Comment> findByPost = commentRepository.findByPost(post);
		return findByPost;
	}

	@Override
	public Comment updateComment(Comment comment,String username, int commentId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		Comment commentUpdate = commentRepository.findById(commentId).orElseThrow(()-> new CustomException("comment not found"));
		if (commentUpdate.getUser().getId()==user.getId()) {
			if (comment.getDescription() != null) {
				commentUpdate.setDescription(comment.getDescription());
				Comment save = commentRepository.save(commentUpdate);
				return save;
			}
		}else throw new CustomException("you can't edit this comment");
		return comment;
		
	}

	@Override
	public String deleteComment(String username,int commentId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CustomException("comment not found"));
      
		if (comment.getPost().getUser().getId()== user.getId() && comment.getUser().getId()==user.getId()) {
			commentRepository.delete(comment);
		}else throw new CustomException("you can't delete this comment");
		return "comment deleted successfully";
	}

}
