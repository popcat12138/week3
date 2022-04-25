package com.gaoyu.controller;

import com.gaoyu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaoyu.entity.Article;
import com.gaoyu.service.ArticleService;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	/********添加文章********/
	//注解将HTTP Get/POST 映射到 特定的处理方法上,帮助简化常用的HTTP方法的映射	
	@GetMapping("/addArticle")
	public String addArticle(Article article) {
		return "article/addArticle";
	}
	
	@PostMapping("/addArticle")
	public String addArticle(HttpSession session,Article article) {
		article.setUser((User)session.getAttribute("user"));
		articleService.addArticle(article);
		return "success";
				//"redirect:showArticle?articleId=";
	}
	/*********查找文章******/
	@GetMapping("/showArticle")
	public String findArticleById(int articleId){
		articleService.findArticleById(articleId);
		return "showArticle";
	}
	/********删除文章*******/
	@GetMapping("/deleteArticle")
	public String deleteArticle() {
		return "addArticle";
	}
	
	@PostMapping("/deleteArticle")
	public String deleteArticle(int articleId) {
		articleService.deleteArticle(articleId);
		return "addArticle";
	}
	/*******修改文章********/
	@GetMapping("/updateArticle")
	public String updateArticle() {
		return "updateArticle";
	}
	
	@PostMapping("/updateArticle")
	public String updateArticle(Article article) {
		articleService.updateArticle(article);
		return "result";
		
	}
	
}
