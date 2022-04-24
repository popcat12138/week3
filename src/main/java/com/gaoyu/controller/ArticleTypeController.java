package com.gaoyu.controller;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @GetMapping("/articleType")
    public String articleType(ArticleType articleType){

        return "article/addArticleType";
    }

    @PostMapping("/addArticleType")
    public String addArticleType(HttpSession session, ArticleType articleType){
        articleType.setUser((User)session.getAttribute("user"));
        articleTypeService.addArticleType(articleType);
        return "success";
    }

    


}
