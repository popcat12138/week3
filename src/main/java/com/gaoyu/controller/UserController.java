package com.gaoyu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.gaoyu.entity.OperLog;
import com.gaoyu.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaoyu.entity.User;
import com.gaoyu.service.UserService;

import java.time.LocalDateTime;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private OperLogService operLogService;
	
	/*******主页*********/
	@GetMapping("/")
	public String index(User user,HttpSession session) {
	    return "index";
	}

	@GetMapping("/main")
	public String main(HttpSession session){
		User user=(User) session.getAttribute("sessionUser");
		System.out.println(user.getPassword()+"kdkkdkdkd");
		return "index";
	}
	
	/***********登陆*******/
	@GetMapping("/login")
	public String login(User user) {

		return "user/login";
	}
	@PostMapping("/login_verify")
	public String login_verify(User user,HttpSession session,Model model) {
		User loginUser=userService.login_verify(user);
		if(loginUser==null){
			model.addAttribute("error","用户名密码错误");
			return "user/login";
		}else{
			//一个是存储sessionUser方便直接用。
			// 一个是方便导航栏的if切换，因为如果写成session.sessionUser.userName会找不到user对象报错
			session.setAttribute("sessionUser",loginUser);
			session.setAttribute("loginUserName",loginUser.getUserName());

			return "redirect:/modifyUserInfo";
		}

	}
	/*********注销**********/
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "success";
	}


	@PostMapping("/findByUserName")
	public String find(User user){
		//userService.findbyUserName(user.getUserName());
		return "result";
	}

	/*******简单添加用户*********/
	@GetMapping("/addUser")
	public String addUser() {
		return "user/addUser";
	}
	
	/*****注册一个新用户*********/
	@GetMapping("/register")
	public String test(User user) {
		return "user/register";
	}

	@PostMapping("/addUser")
	public String addUser(@Valid User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "user/register";
		}
		if(userService.isExistByUserName(user.getUserName())){
			model.addAttribute("error","用户名已存在!");
			return "user/register";
		}
		userService.addUser(user);
		return "user/login";

	}

	/*********修改信息**********/
	@GetMapping("/modifyUserInfo")
	public String modifyUserShow(HttpSession session,Model model){

		model.addAttribute("user",(User)session.getAttribute("sessionUser"));

		return "user/modifyInfo";
	}
	@PostMapping("/modifyInfo")
	public String modifyUserInfo(HttpSession session,@Valid User user,BindingResult result){

		if(result.hasErrors()) {
			return "user/modifyInfo";
		}
		String UUID=((User)session.getAttribute("sessionUser")).getUserUUID();

		User newUser=userService.modifyUserInfo(user,UUID);
		//更新session
		session.setAttribute("sessionUser",newUser);
		session.setAttribute("loginUserName",newUser.getUserName());

		return "redirect:/showUserInfo";
	}

	@GetMapping("/showUserInfo")
	public String showUserInfo(HttpSession session,Model model){
		model.addAttribute("user",(User)session.getAttribute("sessionUser"));
		return "user/showUserInfo";
	}


	/*******删除用户,用不着*******/
	@GetMapping("/delete")
	public String delete(User user) {
		return "delete";
	}

	@PostMapping("/delete")
	public String deletes(User user) {
		userService.deleteUser(user.getUserName());
		return null;
	}
		
	/*******列出所有用户********/
	@GetMapping("/listUser")
	public String listUser(Model model){
		
		model.addAttribute("users",userService.listUser());
		return "listUser";
	}
	
	
	/****测试两个自定义查询方法*****/
	@GetMapping("sqlTest")
	public String sqlTest() {
		return "sqlTest";
	}
	
	@PostMapping("/listTest")
	public String listTest(Model model,String userName,String name) {
		model.addAttribute("users",userService.findTest(userName, name));
		return "listUser";
		
	}
	

	
}

