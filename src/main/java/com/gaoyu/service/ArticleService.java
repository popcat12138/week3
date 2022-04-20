package com.gaoyu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.Article;
import com.gaoyu.repository.ArticleRepository;

import java.util.Optional;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	//@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。
	
	public Article addArticle(Article article) {//保存文章
		return articleRepository.save(article);
		
	}
	
	public void deleteArticle(int articleId) {
		
		articleRepository.deleteById(articleId);
	}
	
	public Article updateArticle(Article article) {
		Article oldArticle=articleRepository.getOne(article.getArticleId());
		oldArticle.setArticleName(article.getArticleName());
		oldArticle.setArticleType(article.getArticleType());
		
		//查询出来，不用执行save方法，就可以保存
		return oldArticle;
		
	}
	public Article findBlogById(int articleId){
		return articleRepository.getById(articleId);
	}

	
}
