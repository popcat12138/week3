package com.gaoyu.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class OperLog {
	@Id
	@GeneratedValue
	private int logId;
	
	private String logContent;
	
	private LocalDateTime createTime;
	
	@ManyToOne()
	private User operator;

	@ManyToOne()
	private User user;
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public OperLog() {
		// TODO Auto-generated constructor stub
	}


	public int getLogId() {
		return logId;
	}


	public void setLogId(int logId) {
		this.logId = logId;
	}


	public String getLogContent() {
		return logContent;
	}


	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}


	public LocalDateTime getCreateTime() {
		return createTime;
	}


	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public User getOperator() {
		return operator;
	}

	public void setOperator(User operator) {
		this.operator = operator;
	}
}
