package com.social.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
