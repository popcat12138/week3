package com.gaoyu.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Log {

	@Id
	@GeneratedValue
	private int logId;

	@Column(length = 200)
	@NotBlank
	private String logContent;
	
	private LocalDateTime createTime=LocalDateTime.now();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Log() {
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

	public void setCreateTime(LocalDateTime logTime) {
		this.createTime = LocalDateTime.now();
	}

	
}
