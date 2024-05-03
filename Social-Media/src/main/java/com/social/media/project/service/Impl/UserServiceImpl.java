package com.social.media.project.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.payload.UserDto;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired 
	PasswordEncoder encoder;

	@Override
	public UserEntity createUser(UserEntity user) {
		user.setCreatedAt(new Date());
		user.setPassword(encoder.encode(user.getPassword()));
		UserEntity save = userRepository.save(user);
		
		return save;
	}

	@Override
	public UserEntity getUserById(int id) throws CustomException {
		UserEntity user = userRepository.findById(id).orElseThrow(()-> new CustomException("user not found"));
		return user;
	}

	@Override
	public UserEntity updateUser(int id, UserEntity user) throws CustomException {
		UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new CustomException("user not found"));
		
		if (user.getUsername() != null) {
			userEntity.setUsername(user.getUsername());
		}
		if (user.getEmail() != null) {
			userEntity.setEmail(user.getEmail());
		}
		if (user.getMobileno() != null) {
			userEntity.setMobileno(user.getMobileno());
		}
		if (user.getName() != null) {
			userEntity.setName(user.getName());
		}
		UserEntity save = userRepository.save(userEntity);
		return save;
	}

	@Override
	public String deleteUser(int id) throws CustomException {
		UserEntity user = userRepository.findById(id).orElseThrow(()-> new CustomException("user not found"));
	     userRepository.delete(user);
		return "user deleted successfully";
	}

	@Override
	public List<UserDto> searchUser(String search) {
		List<UserEntity> users = userRepository.findByUsernameContaining(search);
		List<UserDto> collect = users.stream().map((user)-> mapper.map(user, UserDto.class)).collect(Collectors.toList());
		return collect;
	}
	
	
}
