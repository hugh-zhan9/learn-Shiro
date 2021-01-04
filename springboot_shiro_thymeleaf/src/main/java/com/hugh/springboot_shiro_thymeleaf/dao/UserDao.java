package com.hugh.springboot_shiro_thymeleaf.dao;

import com.hugh.springboot_shiro_thymeleaf.entity.Perms;
import com.hugh.springboot_shiro_thymeleaf.entity.Role;
import com.hugh.springboot_shiro_thymeleaf.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hugh on 2021/1/1
 */
@Mapper
@Repository
public interface UserDao {
    void save(User user);
    User login(String username);
    User findRolesByUserName(String username);
    List<Perms> findByPermsByRoleId(String roleid);
}
