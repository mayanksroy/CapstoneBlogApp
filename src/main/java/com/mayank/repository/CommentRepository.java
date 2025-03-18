package com.mayank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayank.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}
