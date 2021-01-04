package com.hugh.springboot_shiro_thymeleaf.service;

import com.hugh.springboot_shiro_thymeleaf.entity.Perms;
import com.hugh.springboot_shiro_thymeleaf.entity.Role;
import com.hugh.springboot_shiro_thymeleaf.entity.User;

import java.util.List;

/**
 * Created by hugh on 2021/1/1
 */
public interface UserService {
    // 用户注册方法
    void register(User user);

    User login(String username);

    User findRolesByUserName(String username);

    List<Perms> findByPermsByRoleId(String roleid);
}
