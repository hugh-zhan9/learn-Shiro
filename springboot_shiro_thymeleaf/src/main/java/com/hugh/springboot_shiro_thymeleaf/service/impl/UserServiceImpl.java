package com.hugh.springboot_shiro_thymeleaf.service.impl;

import com.hugh.springboot_shiro_thymeleaf.dao.UserDao;
import com.hugh.springboot_shiro_thymeleaf.entity.Perms;
import com.hugh.springboot_shiro_thymeleaf.entity.Role;
import com.hugh.springboot_shiro_thymeleaf.entity.User;
import com.hugh.springboot_shiro_thymeleaf.service.UserService;
import com.hugh.springboot_shiro_thymeleaf.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hugh on 2021/1/1
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 获取随机盐
        String salt = SaltUtils.getSalt(4);
        // 明文密码进行 md5+加盐+hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt,1024);
        user.setSalt(salt);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User login(String username) {
        User user = userDao.login(username);
        return user;
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }


    public List<Perms> findByPermsByRoleId(String roleid){
        return userDao.findByPermsByRoleId(roleid);
    }
}
