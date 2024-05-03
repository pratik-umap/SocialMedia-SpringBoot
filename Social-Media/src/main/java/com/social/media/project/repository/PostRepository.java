package com.social.media.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.media.project.entity.Post;
import com.social.media.project.entity.PostPojo;
import com.social.media.project.entity.UserEntity;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUser(UserEntity user);
	
	@Query(value="select po.post_id as postId, po.post_desc as postDesc, po.post_location as postLocation, po.post_image as postImage, po.created_at as createdAt "
			+ "from t_user_post po\n"
			+ "join t_user_following_followers fo\n"
			+ "on po.fk_user_id=fo.fk_following_user_id \n"
			+ "where fo.fk_user_id=?1",nativeQuery = true)
	List<PostPojo> findPostsByUser(int user);
	
	@Query(value = "SELECT count(*) from t_user_post where fk_user_id=?1",nativeQuery = true)
	int findPostByUser(int user);
	
}
