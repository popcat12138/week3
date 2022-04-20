package com.gaoyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.User;
import com.gaoyu.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User findbyUserName(String userName){
		return userRepository.getById(userName);
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> listUser(){
		return userRepository.findAll();
	}


	public void deleteUser(String userName){
		userRepository.deleteById(userName);
	}

	//自定义，查询用户名包含x和姓名以y开头的User
	public List<User> findTest(String userName,String name){
		return userRepository.findByUserNameContainingAndNameStartingWith(userName, name);
		
	}
	//public List<User> findTest2(String name){
//		return userRepository.findByUserName(name);
//	}
}