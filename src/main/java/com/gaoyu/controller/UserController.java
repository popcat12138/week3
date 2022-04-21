package com.gaoyu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaoyu.entity.User;
import com.gaoyu.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	/*******主页*********/
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/main")
	public String main(HttpSession session){
		User user=(User) session.getAttribute("user");
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
			session.setAttribute("user",loginUser);
			return "redirect:/main";
		}

	}

	@PostMapping("/findByUserName")
	public String find(User user){
		//userService.findbyUserName(user.getUserName());
		return "result";
	}


	//	@PostMapping("/login")
//	public String login_verify(User user){
//		//if()
//	}
//
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
	public String modifyUserShow(HttpSession session,User user){
		//model.addAttribute("info",userService.findbyUserName();

		return "modifyInfo";
	}

	public String modifyUserInfo(User user){
		User newUser=userService.modifyUserInfo(user);
		return "detail";
	}

	/*******删除用户*******/
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

