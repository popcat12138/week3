package com.gaoyu.controller;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {

	@GetMapping("/test")
	public String test(User user, Article article, HttpSession session, Model model) {
		//model.addAttribute("loginUser",(User)session.getAttribute("user"));
		return "article/showArticle";
	}
}
