package com.gaoyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
