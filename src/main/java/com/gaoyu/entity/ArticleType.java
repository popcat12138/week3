package com.gaoyu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
public class ArticleType {
	@Id
	@NotBlank(message = "文章类型不能为空!")
	@Size(min=1,max=15,message = "文章类型长度在1~15之间")
	@Column(length=30)
	private String blogtype;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getBlogtype() {
		return blogtype;
	}


	public void setBlogtype(String blogtype) {
		this.blogtype = blogtype;
	}


	public ArticleType() {
		// TODO Auto-generated constructor stub
	}

}
