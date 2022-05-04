package com.gaoyu.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gaoyu.entity.OperLog;
import com.gaoyu.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.User;
import com.gaoyu.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	//查询某用户信息
	public User findbyUserName(String userName){
		return userRepository.findByUserName(userName);
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
//		PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		user.setPassword(encoder.encode(user.getPassword()));
//		System.out.println(user.getPassword());
		User login_user =userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
		return  login_user;
	}

	//添加一个用户
	public User addUser(User user) {
//		PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	//判断用户是否存在
	public Boolean isExistByUserName(String userName){
		return userRepository.existsUserByUserName(userName);
	}

	//分页查询出所有的用户信息
	public Page<User> listUser(int pageNum, int pageSize){

		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"registerTime"));
		Page<User> users=userRepository.findAll(pageable);
		return users;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username);
		if(user==null){
			throw new UsernameNotFoundException("没有找到用户");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();//权限先为空
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(),user.getPassword(),authorities);

	}
}