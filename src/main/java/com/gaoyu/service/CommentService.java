package com.gaoyu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.Comment;
import com.gaoyu.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

}
