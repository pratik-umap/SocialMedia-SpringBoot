package com.social.media.project.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.project.entity.FollowingAndFollowers;
import com.social.media.project.entity.UserEntity;
import com.social.media.project.exception.CustomException;
import com.social.media.project.repository.FollowAndFollowersRepository;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.service.FollowAndFollowersService;

@Service
public class FollowAndFollowersServiceImpl implements FollowAndFollowersService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowAndFollowersRepository followAndFollowersRepository;
	
	@Override
	public String followUser(String username, int followUserId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		UserEntity followUser = userRepository.findById(followUserId).orElseThrow(()-> new CustomException("user not found"));
      
		FollowingAndFollowers followingAndFollowers = new FollowingAndFollowers();
        followingAndFollowers.setUser(user.getId());
        followingAndFollowers.setFollowUser(followUserId);
        followingAndFollowers.setCreatedAt(new Date());
        
        FollowingAndFollowers save = followAndFollowersRepository.save(followingAndFollowers);
		return "user follow successfully";
	}

	@Override
	public String unFollowUser(String username, int followUserId) throws CustomException {
		UserEntity user = userRepository.findByUsername(username);
		UserEntity followUser = userRepository.findById(followUserId).orElseThrow(()-> new CustomException("user not found"));
      
		FollowingAndFollowers deleteUser = followAndFollowersRepository.findByUserAndFollowuser(user.getId(), followUserId);
	     followAndFollowersRepository.delete(deleteUser);
	     return "user unfollow successfully";
	}

	@Override
	public int getFollowingCntOfUser(UserEntity user) throws CustomException {
//		UserEntity user = userRepository.findByUsername(username);        
		int findFollowingAndFollowersByUser = followAndFollowersRepository.findFollowingandfollowersByUser(user.getId());
		return findFollowingAndFollowersByUser;
	}

	@Override
	public int getFollowersCntOfUser(UserEntity user) throws CustomException {
//		UserEntity user = userRepository.findByUsername(username);        
		int findFollowingAndFollowersByUser = followAndFollowersRepository.findFollowingandfollowersByFollowuser(user.getId());
		return findFollowingAndFollowersByUser;
	}

	@Override
	public boolean followUserExist(String name, int id) {
		UserEntity user = userRepository.findByUsername(name);
		FollowingAndFollowers User = followAndFollowersRepository.findByUserAndFollowuser(user.getId(), id);
		if (User != null) {
			return true;
		}
		return false;
	}

}
