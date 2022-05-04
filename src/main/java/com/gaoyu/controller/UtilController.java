package com.gaoyu.controller;

import com.gaoyu.entity.User;
import com.gaoyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UtilController {
    @Autowired
    UserService userService;
/************图片上传**********/
    @PostMapping("/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImg(@RequestParam("file")MultipartFile file,HttpSession session) {
        Map<String, Object> location = new HashMap<>(2);
        long max = 1024*1023;
        System.out.println("可是可是可是看");

        if(file.getSize() > max){
            return null;
        }
        if (file.isEmpty()) {
            return null;
        }
        String root=session.getServletContext().getRealPath("/img/");
        //System.out.println(root);
        //String root = this.getServletContext().getRealPath("/img/");不能用
        //String root= ClassUtils.getDefaultClassLoader().getResource("static/img/").getPath();添加springsecurity不能用


        String fileName = file.getOriginalFilename();
        //加个时间戳，尽量避免文件名称重复
        String realName =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        String path = root  + realName;
        File dest = new File(path);
        //可以打印一下图片的真实地址
        //System.out.println("图片路径："+path);
        //判断文件是否已经存在
        if (dest.exists()) {
            return null;
        }
        //判断文件父目录是否存在,
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
        } catch (IOException e) {
            return null;
        }
        //返回图片路径
        location.put("location", "/img/" +realName);
        return location;
    }
    /*********头像上传*********/
    @PostMapping("/uploadMyImage")
    public String uploadMyImage(MultipartFile myImg, HttpSession session,User user){
/************中间和tinymce上传图片一样************/
        if(!myImg.isEmpty()){
            String root=session.getServletContext().getRealPath("/img/");
            //System.out.println(root);
            //String root=ClassUtils.getDefaultClassLoader().getResource("static/img/").getPath();
            String fileName = myImg.getOriginalFilename();
            //加个时间戳，尽量避免文件名称重复
            String realName =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
            String path = root  + realName;
            File dest = new File(path);

            System.out.println("图片路径："+path);
            if (dest.exists()) {
                return null;
            }
            //判断文件父目录是否存在,
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                //保存文件
                myImg.transferTo(dest);
            } catch (IOException e) {
                return null;
            }
/************中间和tinymce上传图片一样************/
            String UUID=((User)session.getAttribute("sessionUser")).getUserUUID();
            user.setImgPath( "/img/" +realName);
            User newImg=userService.modifyUserInfo(user,UUID);

            //更新session
            session.setAttribute("sessionUser",newImg);
            return "redirect:/showUserInfo";
        }else {
            return "error";
        }
    }
}
