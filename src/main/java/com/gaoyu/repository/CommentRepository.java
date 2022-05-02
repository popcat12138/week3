package com.gaoyu.repository;

import com.gaoyu.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByArticleIs(Article article);

    List<Comment> findCommentsByParentIdIs(int commentId);

}
