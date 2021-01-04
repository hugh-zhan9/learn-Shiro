package com.hugh.springboot_shiro_thymeleaf.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hugh.springboot_shiro_thymeleaf.cache.RedisCacheManager;
import com.hugh.springboot_shiro_thymeleaf.realm.CustomerMD5Realm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hugh on 2020/12/31
 */
@Configuration
public class ShiroConfig {

    // Thymeleaf整合shiro时的方言依赖
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    // 创建ShiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 配置受限资源和公共资源
        Map<String,String> map = new HashMap<>();
        map.put("/user/login","anon");
        map.put("/user/register","anon");

        map.put("/login.jsp","anon");
        map.put("/register.jsp","anon");

        map.put("/login.html","anon");
        map.put("/register.html","anon");
        //map.put("/**","authc"); //authc:一个过滤器，表示需要认证和授权
        // 默认认证界面路径
        // jsp 页面
        // shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        // thymeleaf
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    // 创建安全管理器
    @Bean
    public DefaultWebSecurityManager getWebDefaultSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    // 创建自定义 realm
    @Bean("realm")
    public Realm getRealm(){
        CustomerMD5Realm customerMD5Realm = new CustomerMD5Realm();
        // 修改凭证校验匹配器和Hash散列次数
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        customerMD5Realm.setCredentialsMatcher(credentialsMatcher);

        // 开启缓存管理（本地缓存）
        /*
        customerMD5Realm.setCacheManager(new EhCacheManager());
        customerMD5Realm.setCachingEnabled(true);   // 开启全局缓存
        customerMD5Realm.setAuthenticationCachingEnabled(true);     // 开启认证缓存
        customerMD5Realm.setAuthenticationCacheName("authenticationCache");
        customerMD5Realm.setAuthorizationCachingEnabled(true);      // 开启授权缓存
        customerMD5Realm.setAuthorizationCacheName("authorizationCache");
        */

        // 使用Redis作为缓存
        customerMD5Realm.setCacheManager(new RedisCacheManager());
        customerMD5Realm.setCachingEnabled(true);   // 开启全局缓存
        customerMD5Realm.setAuthenticationCachingEnabled(true);     // 开启认证缓存
        customerMD5Realm.setAuthenticationCacheName("authenticationCache");
        customerMD5Realm.setAuthorizationCachingEnabled(true);      // 开启授权缓存
        customerMD5Realm.setAuthorizationCacheName("authorizationCache");

        return customerMD5Realm;
    }
}
