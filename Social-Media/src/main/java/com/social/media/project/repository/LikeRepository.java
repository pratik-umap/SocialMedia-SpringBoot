package com.social.media.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.project.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{

	Like findByUserAndPost(int user,int post);
	
	List<Like> findByPost(int post);
	
	
}
