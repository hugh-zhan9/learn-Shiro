package com.hugh.quick;

import com.hugh.quick.realm.CustomerMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import java.util.Arrays;

/**
 * Created by hugh on 2020/12/31
 */
public class TestAuthorization {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        CustomerMD5Realm realm = new CustomerMD5Realm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","19961220");
        try{
            subject.login(token);
            System.out.println("认证状态："+subject.isAuthenticated());
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("认证失败，用户名不存在");
        }catch(IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("认证失败，用户密码错误");
        }

        if(subject.isAuthenticated()){
            System.out.println("========================基于角色权限进行访问控制===============================");
            // 基于角色权限验证
            System.out.println(subject.hasRole("admin"));
            System.out.println(subject.hasRole("user"));
            // 基于多角色权限验证
            System.out.println(subject.hasAllRoles(Arrays.asList("admin","user")));
            // 是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin","user"));
            for (boolean b : booleans){
                System.out.println(b);
            }

            System.out.println("========================基于权限字符串进行访问控制===============================");

            // 基于权限字符串进行访问控制
            System.out.println(subject.isPermitted("user:*:*"));
            boolean[] permittedBooleans = subject.isPermitted("user:create:01","user:update:01");
            for (boolean b: permittedBooleans){
                System.out.println(b);
            }
            System.out.println(subject.isPermittedAll("user:create:01", "user:update:01"));
        }

    }
}
