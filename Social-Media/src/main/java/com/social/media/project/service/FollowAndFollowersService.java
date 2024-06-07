package com.social.media.project.service;

import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;

public interface FollowAndFollowersService {

	String followUser(String username, int followUserId) throws CustomException;

	String unFollowUser(String username, int followUserId) throws CustomException;

	int getFollowingCntOfUser(UserEntity user) throws CustomException;

	int getFollowersCntOfUser(UserEntity user) throws CustomException;

	boolean followUserExist(String name, int id);

}
