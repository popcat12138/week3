package com.gaoyu.controller;

import com.gaoyu.entity.Comment;
import com.gaoyu.entity.Log;
import com.gaoyu.entity.OperLog;
import com.gaoyu.entity.User;
import com.gaoyu.service.*;
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
import java.util.Iterator;

@Controller
public class AdminController {
    @Autowired
    ArticleService articleService;
    @Autowired
    OperLogService operLogService;
    @Autowired
    LogService logService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

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

    @PostMapping("/searchOperLogByDate")
    @ResponseBody
    public String testii(Model model,HttpSession session,
                         LocalDateTime date1, LocalDateTime date2,
                         @RequestParam(value = "pageNum",defaultValue = "0")int pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "20")int pageSize) {

        User operator=(User)session.getAttribute("sessionUser");
        if(!operator.getRole().equals("管理员")){

            return "error";
        }
        Page<OperLog> operLogs=operLogService.getOperLogByDate(pageNum,pageSize,date1,date2);
        return null;
    }

    @GetMapping("/listLog")
    public String ListLog(Model model,HttpSession session,
                          @RequestParam(value = "pageNum",defaultValue = "0")int pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "20")int pageSize){
        User operator=(User)session.getAttribute("sessionUser");
        if(!operator.getRole().equals("管理员")){

            return "error";
        }
        Page<Log> logs=logService.
                getLogList(pageNum,pageSize);
        Iterator<Log> log=logs.iterator();
        while (log.hasNext()){
            System.out.println(log.next().toString());
        }
        model.addAttribute("logs",logs);
        return "admin/listLog";
    }

    @GetMapping("/modifyEnable")
    public String modifyEnable(User user, HttpSession session){
        User operator=(User)session.getAttribute("sessionUser");
        if(!operator.getRole().equals("管理员")){
            return "error";
        }
        if(operator.getUserUUID().equals(user.getUserUUID())){
            return "error";//禁止操作自己
        }
        user=userService.findbyUserName(user.getUserName());
        if (user.isEnable()){
            user.setEnable(false);
        }else {
            user.setEnable(true);
        }
        userService.modifyUserInfo(user,user.getUserUUID());
        return null;
    }

    @GetMapping("/modifyRole")
    public String modifyRole(User user, HttpSession session){
        User operator=(User)session.getAttribute("sessionUser");
        if(!operator.getRole().equals("管理员")){
            return "error";
        }
        if(operator.getUserUUID().equals(user.getUserUUID())){
            return "error";//禁止操作自己
        }
        user=userService.findbyUserName(user.getUserName());
        if (user.getRole().equals("管理员")){
            user.setRole("普通用户");
        }else {
            user.setRole("管理员");
        }
        userService.modifyUserInfo(user,user.getUserUUID());
        return null;
    }
    @GetMapping("/modifyComment")
    public String modifyComment(Comment comment, HttpSession session){
        User operator=(User)session.getAttribute("sessionUser");
        if(!operator.getRole().equals("管理员")){
            return "error";
        }

        comment=commentService.findCommentByCommentId(comment.getCommentId());

        if (comment.getState().equals("正常")){
            comment.setState("禁止");
        }else {
            comment.setState("正常");
        }
        commentService.modifyComment(comment);
        return null;
    }
    @GetMapping("/showUserDetail")
    public String showUserDetail(User user, HttpSession session,Model model){
        User operator=(User)session.getAttribute("sessionUser");
        System.out.println(operator.getRole());
        if(!operator.getRole().equals("管理员")){
            return "error";
        }
        user=userService.findbyUserName(user.getUserName());

        model.addAttribute("user",user);
        return "user/showUserInfo";
    }

}
