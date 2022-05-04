package com.gaoyu.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User implements UserDetails {

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "jpa-uuid")
	private String userUUID;

	@NotBlank(message = "用户名不能为空!")
	@Size(min=1,max=15,message = "用户名长度在1~15之间")
	@Column(length=50,unique = true)
	private String userName;

	@NotBlank(message = "姓名不能为空!")
	@Size(min=1,max=15,message = "用户名长度在1~15之间")
	@Column(length=50)
	private String name;

	@NotBlank(message = "错误!请检查")
	@Size(max=1,message = "错误!请检查")//单选框
	@Column(length=10)
	private String sex;
	
	@NotBlank(message = "密码不能为空!")
	@Size(min=8,max=100,message = "密码长度不符合要求")
	@Column(length=100)
	private String password;
	
	@Past(message = "日期错误!")
	//@NotBlank(message = "日期错误!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
	@NotBlank(message = "电话号码不能为空!")
	@Size(max = 11,min = 11,message = "请输入正确的电话号码!")
	@Column(length=11)
	private String tel;
	
	@Email(message = "请输入正确的电子邮箱!")
	@NotBlank(message = "电子邮箱不能为空!")
	@Size(min=5,max=35,message = "请输入正确的电子邮箱!")
	@Column(length=40)
	private String email;
	
	@NotBlank(message = "微信号不能空!")
	@Pattern(regexp = "^([a-zA-Z])([a-zA-Z0-9_-]){5,19}$" ,message = "请输入正确的微信号!")
	//6-20个字母、数字、下划线和减号；必须以字母开头，字母不区分大小写；不支持设置中文。
	@Column(length=30)
	private String weiXin;
	
	@NotBlank(message = "个性签名不能为空!")
	@Size(min = 1,max = 50,message = "个性签名应在1~50个字之间!")
	@Column(length=70)
	private String myWords;
	
	@Column(length=10)
	private boolean enable=true;

	private String imgPath="img/default.png";

	private String role="管理员";

	private LocalDateTime registerTime=LocalDateTime.now();
	
	private LocalDateTime alterTime;

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getWeiXin() {
		return weiXin;
	}



	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}



	public String getMyWords() {
		return myWords;
	}



	public void setMyWords(String myWords) {
		this.myWords = myWords;
	}



	public boolean isEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public LocalDateTime getRegisterTime() {
		return registerTime;
	}



	public void setRegisterTime(LocalDateTime registerTime) {
		this.registerTime = registerTime;
	}



	public LocalDateTime getAlterTime() {
		return alterTime;
	}



	public void setAlterTime(LocalDateTime alterTime) {
		this.alterTime = alterTime;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public User() {
		
	}

	/*******************/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

