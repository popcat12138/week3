package com.gaoyu.controller;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        articleType.setUser((User)session.getAttribute("sessionUser"));
        articleTypeService.addArticleType(articleType);
        return "success";
    }
    @GetMapping("/listArticleType")
    @ResponseBody
    public List<ArticleType> listArticle(Model model, HttpSession session){
        return articleTypeService.findAllArticleType((User)session.getAttribute("sessionUser"));
    }

    @GetMapping("/deleteArticleType")
    public String deleteArticleType(ArticleType articleType){//将外键user键设置为空来完成删除
        articleType.setArticleTypeId(1);
        articleTypeService.deleteArticleType(articleType);
        return "article/listArticleType";
    }


    @PostMapping("/modifyArticleType")
    public String modifyArticleType(ArticleType articleType){
        articleTypeService.modifyArticleType(articleType);
        return "重定向";
    }

}
