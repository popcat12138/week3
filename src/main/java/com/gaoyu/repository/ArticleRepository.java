package com.gaoyu.repository;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.OperLog;
import com.gaoyu.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Article;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {//实体名，主键类型

	Page<Article> findAllByUserIs(User user,Pageable pageable);

	Page<Article> findAllByArticleTypeAndUserIs(ArticleType articleType,User user,Pageable pageable);

	Page<Article> findByCreateTimeBetween (LocalDateTime date1, LocalDateTime date2, Pageable pageable);

	Page<Article> findTop30ByOrderByCreateTimeDesc(Pageable pageable);

	Page<Article> findAllByArticleTitleContaining(String key,Pageable pageable);
}
