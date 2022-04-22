package com.gaoyu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.gaoyu.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.User;
import com.gaoyu.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	//查询某用户信息
	public User findbyUserName(String userName){
		return findbyUserName(userName);
	}

	//通过用户名查询UUID
	public String findUUID(String userName){
		return findbyUserName(userName).getUserUUID();
	}

	//通过UUID查询用户名
	public Optional<User> findUserNameByUUID(String uuid){
		return userRepository.findById(uuid);
	}

	//登录验证
	public User login_verify (User user){
		User login_user =userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
		return  login_user;
	}

	//添加一个用户
	public User addUser(User user) {
		return userRepository.save(user);
	}

	//判断用户是否存在
	public Boolean isExistByUserName(String userName){
		return userRepository.existsUserByUserName(userName);
	}

	//查询出所有的用户信息
	public List<User> listUser(){
		return userRepository.findAll();
	}

	//删除用户（一般不使用）
	public void deleteUser(String userName){
		userRepository.deleteUserByUserName(userName);
	}

	//自定义，查询用户名包含x和姓名以y开头的User
	public List<User> findTest(String userName,String name){
		return userRepository.findByUserNameContainingAndNameStartingWith(userName, name);
		
	}

	//使用一个方法类，将前端传递过来的不为空的参数，就是要修改的值copy复制来覆盖原始对象
	//即修改的进行修改，不修改的保持不变。
	public User modifyUserInfo(User user,String UUID){

		User oldUser=userRepository.getById(UUID);

		if(oldUser!=null){
			UpdateUtil.copyNullProperties(user,oldUser);
		}
		oldUser.setAlterTime(LocalDateTime.now());//更新修改时间
		userRepository.save(oldUser);
		return userRepository.getById(UUID);
	}

}