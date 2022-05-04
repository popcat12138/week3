package com.gaoyu.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;


@Entity
public class ALike {
    @Id
    @GeneratedValue
    public int likeId;
    //用来前端展示
    public boolean enableLike;
    //用来前端展示
    public int countsNum;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    public User user;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    public Article article;

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public boolean isEnableLike() {
        return enableLike;
    }

    public void setEnableLike(boolean enableLike) {
        this.enableLike = enableLike;
    }

    public int getCountsNum() {
        return countsNum;
    }

    public void setCountsNum(int countsNum) {
        this.countsNum = countsNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
