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
	//另一种更新方法,要使用save。当主键不改变时，不更新的值也不会设置成null
	public Article updateTest(Article article){
		return articleRepository.save(article);
	}


	public Article findArticleById(int articleId){
		return articleRepository.getById(articleId);
	}

	
}
