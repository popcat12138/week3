package com.gaoyu.controller;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.Log;
import com.gaoyu.entity.OperLog;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleService;
import com.gaoyu.service.LogService;
import com.gaoyu.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class TestController {

	@Autowired
	ArticleService articleService;
	@Autowired
	OperLogService operLogService;
	@Autowired
	LogService logService;


	@GetMapping("/test")
	public String test(User user, Article article, HttpSession session, Model model) {
		//model.addAttribute("loginUser",(User)session.getAttribute("user"));
		return "success";//article/showArticle
	}

//	@GetMapping("/testo")
//	@ResponseBody
//	public List<Article> test0(User user, Article article, HttpSession session, Model model) {
//		return articleService.findLastArticle();
//	}


	@GetMapping("/testn")
	@ResponseBody
	public String testn(User user, Article article, HttpSession session, Model model,
							   @RequestParam(value = "pageNum", defaultValue = "0")int pageNum,
							   @RequestParam(value = "pageSize",defaultValue = "10")int pageSize) {
		user=(User)session.getAttribute("sessionUser");
		Page<Article> articleList=articleService.findAllByUser(user,pageNum,pageSize);
		return null;
	}

	@GetMapping("/testoP")
	@ResponseBody
	public OperLog testOp(User user,OperLog operLog, Article article, HttpSession session) {
		operLog.setCreateTime(LocalDateTime.now());
		operLog.setOperator((User)session.getAttribute("sessionUser"));
		operLog.setLogContent("kakakak");
		return operLogService.addOperLog(operLog);
	}
	@GetMapping("/testpp")
	@ResponseBody
	public Article findArticleById(){
		//articleService.findArticleById(articleId);
		return articleService.findArticleById(292);
	}

//	@GetMapping("/listLog")
//	public String ListLog(Model model,
//							  @RequestParam(value = "pageNum",defaultValue = "0")int pageNum,
//							  @RequestParam(value = "pageSize",defaultValue = "20")int pageSize){
//		Page<Log> logs=logService.
//				getLogList(pageNum,pageSize);
//		Iterator<Log> log=logs.iterator();
//		while (log.hasNext()){
//			System.out.println(log.next().toString());
//		}
//		model.addAttribute("logs",logs);
//		return "admin/listLog";
//	}
}

