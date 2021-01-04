package com.hugh.quick;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * Created by hugh on 2020/12/31 10:38
 */
public class TestAuthenticator{
    public static void main(String[] args) {
        // 创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 给安全管理器设置Realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));

        // 全局工具类SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // 关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();

        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
        // 用户认证
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
    }
}
