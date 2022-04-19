package com.gaoyu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.repository.ArticleTypeRepository;

@Service
public class ArticleTypeService {

	@Autowired
	private ArticleTypeRepository articleTypeRepository;
	
	public ArticleType addArticleType(ArticleType articleType) {
		return articleTypeRepository.save(articleType);
	}
	
	public void deleteArticleType(ArticleType articleType) {
		
	}
	
}
