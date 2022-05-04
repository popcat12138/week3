package com.gaoyu.service;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.ArticleType;
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


	public Comment commentEnable(Comment comment){

		Comment oldComment=commentRepository.getById(comment.getCommentId());
			if(comment.getState().equals("正常")){
				comment.setState("禁止");
			}else {
				comment.setState("正常");
			}
		if(oldComment!=null){
			UpdateUtil.copyNullProperties(comment,oldComment);
		}
		return commentRepository.save(oldComment);
	}

	public List<Comment> findCommentByArticleId(Article article){
		return commentRepository.findCommentByArticleIs(article);
	}

	public List<Comment> findCommentByParentId(int parentId){
		return commentRepository.findCommentsByParentIdIs(parentId);
	}

//	public String CommentShow(Article article){
//		for(Comment comment : findCommentByArticleId(article)) {
//			for(Comment commentSlave:findCommentByParentId(comment.getCommentId())){
//				String
//			}
//		}
//	}
	public Comment findCommentByCommentId(int commentId){
		return commentRepository.getById(commentId);
	}

	//使用一个方法类，将前端传递过来的不为空的参数，就是要修改的值copy复制来覆盖原始对象
	//即修改的进行修改，不修改的保持不变。
	public Comment modifyComment(Comment comment){

		Comment oldComment=commentRepository.getById(comment.getCommentId());

		if(oldComment!=null){
			UpdateUtil.copyNullProperties(comment,oldComment);
		}
		commentRepository.save(oldComment);
		return null;
	}

}
