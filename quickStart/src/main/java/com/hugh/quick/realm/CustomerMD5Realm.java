package com.hugh.quick.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 自定义Realm实现MD5加盐，将认证、授权数据的来源转化为数据库的实现
 *
 * Created by hugh on 2020/12/31 11:37
 */
public class CustomerMD5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("admin");
        arrayList.add("user");

        String principal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("进入到权限验证阶段========"+ principal);
        // 可以看到，每次进行权限验证都要执行这个方法，所以一旦大量验证就要耗费大量资源
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("admin");
        authorizationInfo.addRoles(arrayList);

        authorizationInfo.addStringPermission("user:create:01");
        authorizationInfo.addStringPermission("user:update:01");
        return authorizationInfo;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户名
        String principal = (String) authenticationToken.getPrincipal();
        // 根据用户名去查询数据库验证，这里先模拟查询
        if("zhangsan".equals(principal)){
            // 参数1：返回数据库中正确的用户名 参数2：返回数据库中正确密码 参数3：提供当前realm名称
            // SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("zhangsan","89f64208bfa52aa4da900aaeb906438c", ByteSource.Util.bytes("zyk."),this.getName());
            // 使用加盐处理，参数3表示加入的盐：ByteSource.Util.bytes("zyk.")来表示盐的内容
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("zhangsan","d059de448d8737b2f66106170e866663", ByteSource.Util.bytes("zyk."),this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
