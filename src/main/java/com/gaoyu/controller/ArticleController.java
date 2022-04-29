package com.gaoyu.controller;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleTypeService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gaoyu.entity.Article;
import com.gaoyu.service.ArticleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleTypeService articleTypeService;
	
	/********添加文章********/
	//注解将HTTP Get/POST 映射到 特定的处理方法上,帮助简化常用的HTTP方法的映射	
	@GetMapping("/addArticle")
	public String addArticle(Article article, Model model,HttpSession session) {
		model.addAttribute("TypeList",articleTypeService.findAllArticleType((User) session.getAttribute("sessionUser")));
		return "article/addArticle";
	}
	
	@PostMapping("/addArticle")
	public String addArticle(HttpSession session,Article article) {
		article.setUser((User)session.getAttribute("sessionUser"));
		articleService.addArticle(article);

		return "redirect:showArticle?articleId=354";
				//"redirect:showArticle?articleId=";
	}
	/*********查找文章******/

	@GetMapping("/showArticle")
	public String findArticleById(int articleId,Model model){
		model.addAttribute("article",articleService.findArticleById(articleId));
		return "article/showArticle";
	}
	@GetMapping("/listArticleByType")
	public String listArticleByType(ArticleType articleType){
		articleService.findArticleById(articleType.getArticleTypeId());
		return "article/showArticle";
	}

	/********删除文章*******/
	@GetMapping("/deleteArticle")
	public String deleteArticle() {
		return "addArticle";
	}
	
	@PostMapping("/deleteArticle")
	public String deleteArticle(int articleId) {
		articleService.deleteArticle(articleId);
		return "/addArticle";
	}
	/*******修改文章********/
	@GetMapping("/modifyArticle")
	public String modifyArticle(int articleId,Model model) {
		model.addAttribute("article",articleService.findArticleById(articleId));
		return "article/modifyArticle";
	}
	
	@PostMapping("/modifyArticle")
	public String modifyArticle(Article article) {
		articleService.modifyArticle(article);
		return "article/showArticle";
		
	}
	
}
