package com.gaoyu.repository;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.ALike;
import com.gaoyu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ALikeRepository extends JpaRepository<ALike, Integer> {
    //显示赞的数量
    Integer countAllByArticleIs(Article article);

    //查询用户是否可以点赞
    Boolean existsAllByArticleAndUserIs(Article article, User user);

}
