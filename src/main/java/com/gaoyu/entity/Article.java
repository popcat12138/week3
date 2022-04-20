package com.gaoyu.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Article {

	@Id
	@GeneratedValue
	private int articleId;//文章类型id号（唯一）

	@Column(length = 30)
	@NotBlank(message = "文章标题不能为空!")
	@Size(min=1,max=15,message = "标题长度在1~25之间")
	private String articleTitle;//文章标题

	@Column(length = 8000)
	@NotBlank(message = "文章内容不能为空!")
	@Size(min = 1,max = 8000,message = "文章内容过长!")
	private String content;//文章内容
	
	private LocalDateTime createTime=LocalDateTime.now();//创建时间
	
	private LocalDateTime upDateTime;//修改时间
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private User user;
	
	@ManyToOne(cascade = {CascadeType.ALL,CascadeType.MERGE})
	private ArticleType articleType;
	
	public Article() {
		
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpDateTime() {
		return upDateTime;
	}

	public void setUpDateTime(LocalDateTime upDateTime) {
		this.upDateTime = upDateTime;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public ArticleType getArticleType() {
		return articleType;
	}


	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}


	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleName() {
		return articleTitle;
	}
	public void setArticleName(String articleName) {
		this.articleTitle = articleTitle;
	}
	
	
}
