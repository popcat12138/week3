package com.gaoyu.controller;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.Comment;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleTypeService;
import com.gaoyu.service.CommentService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.gaoyu.entity.Article;
import com.gaoyu.service.ArticleService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private CommentService commentService;
	
	/********添加文章********/
	//注解将HTTP Get/POST 映射到 特定的处理方法上,帮助简化常用的HTTP方法的映射	
	@GetMapping("/addArticle")
	public String addArticle(RedirectAttributes redirect,Article article, Model model,HttpSession session,ArticleType articleType) {
	    User user=(User)session.getAttribute("sessionUser");
		if(articleTypeService.findEnableType(user).isEmpty()){
            redirect.addAttribute("warning","当前可用博客类型为空！");
			return "redirect:articleManager";
		}
        System.out.println();
		model.addAttribute("TypeList",articleTypeService.findEnableType(user));
		return "article/addArticle";
	}
	
	@PostMapping("/addArticle")
	public String addArticle(RedirectAttributes redirect, HttpSession session, Article article, ArticleType articleType) {
		User user=(User)session.getAttribute("sessionUser");
		article.setUser(user);
		article.setArticleType(articleType);
		articleService.addArticle(article);

		redirect.addAttribute("warning","完成");
		return "redirect:articleManager";
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
	/******模糊搜索*******/
	@PostMapping("/searchListArticle")
	public String searchListArticle(RedirectAttributes redirect,String key,Model model,
									@RequestParam(value = "pageNum", defaultValue = "0")int pageNum,
									@RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

		Page<Article> articleList=articleService.searchAllByKey(key,pageNum,pageSize);

		model.addAttribute("articleList",articleList);
		return "article/listArticle";
	}

	/********删除文章*******/
	@GetMapping("/deleteArticle")
	public String deleteArticle(RedirectAttributes redirect,int articleId) {
		articleService.deleteArticle(articleId);
		redirect.addAttribute("warning","完成");
		return "redirect:articleManager";
	}

	/*******修改文章********/
	@GetMapping("/modifyArticle")
	public String modifyArticle(int articleId,Model model,HttpSession session) {
		User user=(User)session.getAttribute("sessionUser");
		model.addAttribute("article",articleService.findArticleById(articleId));
		model.addAttribute("TypeList",articleTypeService.findEnableType(user));
		return "article/modifyArticle";
	}
	
	@PostMapping("/modifyArticle")
	public String modifyArticle(Article article) {
		articleService.modifyArticle(article);
		return "article/showArticle";
		
	}
	/********文章列表******/
	@GetMapping("/listArticle")
	public String listArticle(Model model,
							  @RequestParam(value = "pageNum", defaultValue = "0")int pageNum,
							  @RequestParam(value = "pageSize",defaultValue = "10")int pageSize) {

		System.out.println("llallala");
		Page<Article> articleList=articleService.findLastArticle(pageNum,pageSize);
		model.addAttribute("articleList",articleList);
		return "article/listArticle";

	}
	/*******文章管理********/
	@GetMapping("/articleManager")
	public String articleMananger(HttpSession session, Model model, String warning, ArticleType articleType,
	@RequestParam(value = "pageNum", defaultValue = "0")int pageNum,
	@RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
		User user=(User)session.getAttribute("sessionUser");
		List<ArticleType> articleTypeList=articleTypeService.findAllArticleType(user);
		Page<Article> articleList=articleService.findAllByUser(user,pageNum,pageSize);
		Page<Article> articleListl=articleService.findLastArticle(pageNum,pageSize);
		model.addAttribute("articleTypeList",articleTypeList);
		model.addAttribute("articleList",articleList);
		model.addAttribute("warning",warning);
		return "article/articleManager";
	}

}
