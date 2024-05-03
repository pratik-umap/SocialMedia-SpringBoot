package com.social.media.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.project.entity.Comment;
import com.social.media.project.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByPost(Post post);
}
