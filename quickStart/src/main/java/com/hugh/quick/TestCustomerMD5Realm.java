package com.hugh.quick;

import com.hugh.quick.realm.CustomerMD5Realm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 测试加盐的方式
 *
 * Created by hugh on 2020/12/31
 */
public class TestCustomerMD5Realm {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        // 使用md5算法进行解密
        CustomerMD5Realm realm = new CustomerMD5Realm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 使用的算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数
        credentialsMatcher.setHashIterations(1024);
        // 设置realm使用hash凭证匹配器
        realm.setCredentialsMatcher(credentialsMatcher);

        securityManager.setRealm(realm);
        // 设置全局工具类的securityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 通过全局工具类获取认证主体
        Subject subject = SecurityUtils.getSubject();
        // 认证
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
    }
}
