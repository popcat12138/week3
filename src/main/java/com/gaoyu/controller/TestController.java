package com.gaoyu.controller;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.OperLog;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleService;
import com.gaoyu.service.OperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Controller
public class TestController {

	@Autowired
	ArticleService articleService;
	@Autowired
	OperLogService operLogService;

	@GetMapping("/test")
	public String test(User user, Article article, HttpSession session, Model model) {
		//model.addAttribute("loginUser",(User)session.getAttribute("user"));
		return "success";//article/showArticle
	}

	@GetMapping("/testo")
	@ResponseBody
	public List<Article> test0(User user, Article article, HttpSession session, Model model) {
		return articleService.findLastArticle();
	}

	@GetMapping("/testd")
	@ResponseBody
	public List<Article> testd(User user, Article article, HttpSession session, Model model) {
		LocalDateTime date= LocalDateTime.now();
		LocalDateTime dateTime=LocalDateTime.of(2020,10,11,11,11);
		return articleService.findAllByTime(dateTime,date);
	}

	@GetMapping("/testn")
	@ResponseBody
	public List<Article> testn(User user, Article article, HttpSession session, Model model) {
		user=(User)session.getAttribute("sessionUser");
		return articleService.findAllByUser(user);
	}
	@GetMapping("/listOperLog")
	public String ListOperLog(Model model,
							  @RequestParam(value = "pageNum",defaultValue = "0")int pageNum,
							  @RequestParam(value = "pageSize",defaultValue = "20")int pageSize){
		Page<OperLog> operLogs=operLogService.getOperLogList(pageNum,pageSize);
		Iterator<OperLog> operlog=operLogs.iterator();
		while (operlog.hasNext()){
			System.out.println(operlog.next().toString());
		}
		model.addAttribute("operLogs",operLogs);
		return "admin/listOperLog";
	}
	@GetMapping("/testoP")
	@ResponseBody
	public OperLog testOp(User user,OperLog operLog, Article article, HttpSession session) {
		operLog.setCreateTime(LocalDateTime.now());
		operLog.setOperator((User)session.getAttribute("sessionUser"));
		operLog.setLogContent("kakakak");
		return operLogService.addOperLog(operLog);
	}
}

