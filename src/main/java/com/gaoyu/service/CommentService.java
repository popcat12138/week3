package com.gaoyu.service;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.User;
import com.gaoyu.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.Comment;
import com.gaoyu.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}


	public void commentEnable(Comment comment){

		Comment oldComment=commentRepository.getById(comment.getCommentId());
			if(comment.getState().equals("正常")){
				comment.setState("禁止");
			}else {
				comment.setState("正常");
			}
		if(oldComment!=null){
			UpdateUtil.copyNullProperties(comment,oldComment);
		}
		commentRepository.save(oldComment);
	}

	public List<Comment> findCommentByArticleId(Article article){
		return commentRepository.findCommentByArticleIs(article);
	}

	public List<Comment> findCommentByParentId(int commentId){
		return commentRepository.findCommentsByParentIdIs(commentId);
	}

//	public String CommentShow(Article article){
//		for(Comment comment : findCommentByArticleId(article)) {
//			for(Comment commentSlave:findCommentByParentId(comment.getCommentId())){
//				String
//			}
//		}
//	}


}
