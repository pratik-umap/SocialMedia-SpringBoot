package com.social.media.project.service;

import java.util.List;

import com.social.media.project.entity.Comment;
import com.social.media.project.exception.CustomException;

public interface CommentService {

	Comment createComment(Comment comment,String username,int postId) throws CustomException;

	List<Comment> getAllCommentOfPost(int postId) throws CustomException;

	Comment updateComment(Comment comment, String username, int commentId) throws CustomException;

	String deleteComment(String username,int commentId) throws CustomException;

}