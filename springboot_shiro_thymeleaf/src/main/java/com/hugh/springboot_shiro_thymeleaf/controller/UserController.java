package com.hugh.springboot_shiro_thymeleaf.controller;

import com.hugh.springboot_shiro_thymeleaf.entity.User;
import com.hugh.springboot_shiro_thymeleaf.service.UserService;
import com.hugh.springboot_shiro_thymeleaf.utils.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hugh on 2020/12/31
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("认证失败，用户名不存在");
        }catch(IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("认证失败，用户密码错误");
        }
        return "user/login";
    }

    @RequestMapping("logout")
    @RequiresRoles("user")
    public String logout(){
        SecurityUtils.getSubject().logout();
        // return "redirect:/login.jsp";
        return "login";
    }

    @RequestMapping("register")
    public String regist(User user){
        try{
            userService.register(user);
        }catch (Exception e){
            e.printStackTrace();
            // return "redirect:/register.jsp";
            return "register";
        }
        return "login";
    }

    @RequestMapping("getImage")
    public void getImage(HttpSession httpSession, HttpServletResponse response) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        httpSession.setAttribute("code",code);
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220,60,outputStream,code);
    }
}
