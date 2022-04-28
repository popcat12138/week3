package com.gaoyu.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class ArticleType {
	@Id
	@GeneratedValue
	private int articleTypeId;

	@NotBlank(message = "文章类型不能为空!")
	@Size(min=1,max=15,message = "文章类型长度在1~15之间")
	@Column(length=30)
	private String articleTypeName;
//不能设置级联保存，即casadeType.persist,否则在插入时会报detached entity passed to persist错
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	private User user;
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public int getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(int articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getArticleTypeName() {
		return articleTypeName;
	}

	public void setArticleTypeName(String articleTypeName) {
		this.articleTypeName = articleTypeName;
	}

	public ArticleType() {
		// TODO Auto-generated constructor stub
	}

}
