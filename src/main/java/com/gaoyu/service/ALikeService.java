package com.gaoyu.service;

import com.gaoyu.entity.ALike;
import com.gaoyu.entity.Article;
import com.gaoyu.entity.User;
import com.gaoyu.repository.ALikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ALikeService {
    @Autowired
    private ALikeRepository aLikeRepository;

    public Integer countNum(Article article){
        return aLikeRepository.countAllByArticleIs(article);
    }

    public Boolean enableLike(Article article, User user){
        return aLikeRepository.existsAllByArticleAndUserIs(article,user);
    }

    public void addLike(ALike aLike){
        aLikeRepository.save(aLike);
    }

    public void deleteLike(ALike aLike){
        aLikeRepository.delete(aLike);
    }

}
