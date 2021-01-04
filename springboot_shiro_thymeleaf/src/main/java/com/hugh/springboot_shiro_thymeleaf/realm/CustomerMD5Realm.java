package com.hugh.springboot_shiro_thymeleaf.realm;

import com.hugh.springboot_shiro_thymeleaf.entity.Perms;
import com.hugh.springboot_shiro_thymeleaf.entity.Role;
import com.hugh.springboot_shiro_thymeleaf.entity.User;
import com.hugh.springboot_shiro_thymeleaf.salt.MyByteSource;
import com.hugh.springboot_shiro_thymeleaf.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerMD5Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /*
    * 可以看到关于授权这一部分如果每次都直接访问数据库的话会给数据库带来很大的压力
    * 所以实际情况中一般使用缓存来管理授权信息。
     */

    // 授权(角色信息和权限信息)
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findRolesByUserName(principal);
        List<Role> roles = user.getRoles();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        if(!CollectionUtils.isEmpty(roles)){
            roles.forEach(role -> {
                authorizationInfo.addRole(role.getName());
                List<Perms> perms = userService.findByPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm -> authorizationInfo.addStringPermission(perm.getName()));
                }
            });
             return authorizationInfo;
        }
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        User user = userService.login(principal);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), new MyByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
