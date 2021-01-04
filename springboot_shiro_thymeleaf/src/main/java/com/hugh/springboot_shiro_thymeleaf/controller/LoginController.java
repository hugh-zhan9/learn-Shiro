package com.hugh.springboot_shiro_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hugh on 2020/12/31
 */
@Controller
public class LoginController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
}
