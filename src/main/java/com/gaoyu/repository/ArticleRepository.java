package com.gaoyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {//实体名，主键类型

	
	
}
