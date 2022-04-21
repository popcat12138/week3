package com.gaoyu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaoyu.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	//自定义，查询用户名包含x和姓名以y开头的User
	List<User> findByUserNameContainingAndNameStartingWith(String userName,String name);
	
	//注解方式  //查询名字带有x的用户 
//	@Query(value = "select u from User u where u.userName like %:userName%")
//	List<User> findByUserName(@Param("userName") String userName);

	//登录验证
	User findByUserNameAndPassword(String userName,String password);


	//判断是否存在此用户
	Boolean existsUserByUserName(String userName);

	//按用户名查找
	User findByUserName(String userName);

	//按用户名删除
	Boolean deleteUserByUserName(String userName);

}
