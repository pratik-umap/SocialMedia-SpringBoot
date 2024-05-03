package com.social.media.project.service;

import com.social.media.project.exception.CustomException;

public interface LikeService {

	String likePost(String username, int postId) throws CustomException;

	String unLikePost(String username, int postId) throws CustomException;

}
