package com.gaoyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByArticleIs(int blogId);

   // List<Comment> findCommentsBy

}
