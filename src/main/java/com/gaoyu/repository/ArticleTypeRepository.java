package com.gaoyu.repository;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.Log;
import com.gaoyu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.ArticleType;

import java.util.Date;
import java.util.List;

public interface ArticleTypeRepository extends JpaRepository<ArticleType,Integer > {

    //List<Log> findByCreateTimeBetween(Date date1, Date date2);
    List<ArticleType> findAllByUserIs(User user);

    Boolean existsArticleTypeByUserAndArticleTypeNameIs(User user,String articleTypeName);
}
