package com.gaoyu.controller;

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
	private UserService service;
	
	/*******主页*********/
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	/***********登陆*******/
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	@PostMapping("/login_verify")
	public String login_verify(User user) {
		String password=service.findbyUserName(user.getUserName()).getPassword();
		if(!user.getPassword().equals(password)){
			return "user/login";
		}
		return "result";
	}

	@PostMapping("/findByUserName")
	public String find(User user){
		service.findbyUserName(user.getUserName());
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
	public String addUser(@Valid User user,BindingResult result) {
		if(result.hasErrors()) {
			return "user/register";
		}
		service.addUser(user);
		return "result";
	}
		
	/*******列出所有用户********/
	@GetMapping("/listUser")
	public String listUser(Model model){
		
		model.addAttribute("users",service.listUser());	
		return "listUser";
	}
	
	
	/****测试两个自定义查询方法*****/
	@GetMapping("sqlTest")
	public String sqlTest() {
		return "sqlTest";
	}
	
	@PostMapping("/listTest")
	public String listTest(Model model,String userName,String name) {
		model.addAttribute("users",service.findTest(userName, name));
		return "listUser";
		
	}
	
//	@PostMapping("/listTest2")
//	public String listTest2(Model model,String userName) {
//		model.addAttribute("users",service.findTest2(userName));
//		return "listUser";
//
//	}
	
}

