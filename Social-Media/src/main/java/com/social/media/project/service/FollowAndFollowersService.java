package com.social.media.project.service;

import com.social.media.project.exception.CustomException;

public interface FollowAndFollowersService {

	String followUser(String username, int followUserId) throws CustomException;

	String unFollowUser(String username, int followUserId) throws CustomException;

	int getFollowingCntOfUser(String username) throws CustomException;

	int getFollowersCntOfUser(String username) throws CustomException;

}
