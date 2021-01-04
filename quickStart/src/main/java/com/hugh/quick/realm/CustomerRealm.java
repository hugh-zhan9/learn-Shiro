package com.hugh.quick.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm实现，将认证、授权数据的来源转化为数据库的实现
 *
 * Created by hugh on 2020/12/31 11:37
 */
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        // 根据用户名去查询数据库验证，这里先模拟查询
        if("zhangsan".equals(principal)){
            // 参数1：返回数据库中正确的用户名 参数2：返回数据库中正确密码 参数3：提供当前realm名称
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("zhangsan","123456",this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
