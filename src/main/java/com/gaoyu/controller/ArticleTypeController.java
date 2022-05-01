package com.gaoyu.controller;

import com.gaoyu.entity.ArticleType;
import com.gaoyu.entity.User;
import com.gaoyu.service.ArticleService;
import com.gaoyu.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/articleType")
    public String articleType(ArticleType articleType){

        return "articleManager";
    }

    @PostMapping("/addArticleType")
    public String addArticleType(RedirectAttributes redirect, HttpSession session, ArticleType articleType){
        User user=(User)session.getAttribute("sessionUser");
        if(articleTypeService.isExistTypeName(user,articleType)){
            redirect.addAttribute("warning","存在相同类型名");
            return "redirect:articleManager";
        }

        articleType.setUser(user);
        articleTypeService.addArticleType(articleType);

        redirect.addAttribute("warning","添加完成");
        return "redirect:articleManager";
    }
    @GetMapping("/listArticleType")
    public List<ArticleType> listArticle(Model model, HttpSession session){
        return articleTypeService.findAllArticleType((User)session.getAttribute("sessionUser"));
    }

    @GetMapping("/deleteArticleType")
    public String deleteArticleType(RedirectAttributes redirect,ArticleType articleType,HttpSession session){//将外键user键设置为空来完成删除
        if(articleService.findAllByType(articleType).isEmpty()){
            articleTypeService.deleteArticleType(articleType);
            redirect.addAttribute("warning","删除成功！");
            return "redirect:articleManager";
        }
        redirect.addAttribute("warning","请先移除类型下全部文章");
        return "redirect:articleManager";
    }

    @PostMapping("/modifyArticleType")
    public String modifyArticleType(ArticleType articleType){
        articleTypeService.modifyArticleType(articleType);
        return "redirect:articleManager?warning=\"+\"完成\"";
    }
    @GetMapping("switchEnable")
    public String switchEable(ArticleType articleType){
        if (articleTypeService.)
    }

}
