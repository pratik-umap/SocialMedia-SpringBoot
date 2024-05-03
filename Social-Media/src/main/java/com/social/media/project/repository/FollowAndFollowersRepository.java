package com.social.media.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.media.project.entity.FollowingAndFollowers;

public interface FollowAndFollowersRepository extends JpaRepository<FollowingAndFollowers, Integer>{

	FollowingAndFollowers findByUserAndFollowuser(int user,int followuser);
	
	@Query(value = "SELECT count(*) from t_user_following_followers where fk_user_id=?1 ",nativeQuery = true)
	int findFollowingandfollowersByUser(int user);
	
	@Query(value = "SELECT count(*) from t_user_following_followers where fk_following_user_id=?1 ",nativeQuery = true)
	int findFollowingandfollowersByFollowuser(int followuser);
}
