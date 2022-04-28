package com.gaoyu.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private int commentId;
	
	private String content;
	
	private LocalDateTime createTime;
	
	private int parentId;
	
	private String state;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	public User master;//代表这条评论的发起者

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	public User slave;//代表这条评论回复的是谁

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	public Article article;//博客ID

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getMaster() {
		return master;
	}

	public void setMaster(User master) {
		this.master = master;
	}

	public User getSlave() {
		return slave;
	}

	public void setSlave(User slave) {
		this.slave = slave;
	}
}
