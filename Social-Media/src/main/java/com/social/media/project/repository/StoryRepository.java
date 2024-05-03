package com.social.media.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.media.project.entity.Story;
import com.social.media.project.entity.StoryPojo;

public interface StoryRepository extends JpaRepository<Story, Integer>{

	@Query(value = "SELECT st.id as id,st.image_path as imagePath,st.fk_user_id as user,st.created_at as createdAt from t_user_story st\n"
			+ "join t_user_following_followers fo\n"
			+ "on st.fk_user_id=fo.fk_following_user_id\n"
			+ "where fo.fk_user_id=?1 ",nativeQuery = true)
	List<StoryPojo>findStoryByUser(int user);
	
}
