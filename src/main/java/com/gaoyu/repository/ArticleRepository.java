package com.gaoyu.repository;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.OperLog;
import com.gaoyu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Article;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {//实体名，主键类型

	List<Article> findAllByUserIs(User user);

	List<Article> findAllByArticleTypeIs(ArticleType article);

	List<Article> findByCreateTimeBetween (LocalDateTime date1, LocalDateTime date2);

	List<Article> findTop30ByOrderByCreateTimeDesc();


}
