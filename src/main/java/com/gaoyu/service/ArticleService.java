package com.gaoyu.service;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.User;
import com.gaoyu.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.Article;
import com.gaoyu.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

	public Article modifyArticle(Article article){

		Article oldArticle=articleRepository.getById(article.getArticleId());

		if(oldArticle!=null){
			UpdateUtil.copyNullProperties(article,oldArticle);
		}
		oldArticle.setUpdateTime(LocalDateTime.now());
		//更新修改时间
		articleRepository.save(oldArticle);
		return articleRepository.getById(article.getArticleId());
	}


	public Article findArticleById(int articleId){
		return articleRepository.getById(articleId);
	}

	public Page<Article> findAllByUser(User user,int pageNum,int pageSize){
		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
		Page<Article> articles=articleRepository.findAllByUserIs(user,pageable);
		return articles;
	}

	public Page<Article> findAllByTime(LocalDateTime date1, LocalDateTime date2,int pageNum,int pageSize){
		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
		Page<Article> articles=articleRepository.findByCreateTimeBetween(date1,date2,pageable);
		return articles;
	}

	public Page<Article> findLastArticle(int pageNum,int pageSize){
		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
		Page<Article> articles= articleRepository.findTop30ByOrderByCreateTimeDesc(pageable);
		return articles;
	}
	public Page<Article> findAllByType(ArticleType articleType,User user,int pageNum,int pageSize){
		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
		Page<Article> articles=articleRepository.findAllByArticleTypeAndUserIs(articleType,user,pageable);
	    return articles;
	}

	public Page<Article> searchAllByKey(String key,int pageNum,int pageSize){
		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
		Page<Article> articles=articleRepository.findAllByArticleTitleContaining(key,pageable);
		return articles;
	}
	
}
