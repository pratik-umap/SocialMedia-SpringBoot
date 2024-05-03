package com.social.media.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.project.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByUsername(String username);
	
	List<UserEntity> findByUsernameContaining(String username);
	
}
