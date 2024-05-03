package com.social.media.project.service;

import java.util.List;

import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.UserDto;

public interface UserService {

	UserEntity createUser(UserEntity user);

	UserEntity getUserById(int id) throws CustomException;

	UserEntity updateUser(int id, UserEntity user) throws CustomException;

	String deleteUser(int id) throws CustomException;

	List<UserDto> searchUser(String search);

}
