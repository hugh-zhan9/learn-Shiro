<%--
  Created by IntelliJ IDEA.
  User: hugh_
  Date: 2020/8/30
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
</head>
<body>
登陆成功<br>
<br>
<a href="/shiro/user/logout">退出登录</a><br>
<!--这个标签表示权限登陆时可见-->
<shiro:authenticated>1</shiro:authenticated>
<!--这个标签表示访客登陆时可见-->
<shiro:guest>2</shiro:guest>
<shiro:hasAnyRoles name="admin">3</shiro:hasAnyRoles>
<shiro:notAuthenticated>4</shiro:notAuthenticated>
<!--这个标签表示缺少当前权限者可见-->
<shiro:lacksPermission name="user:*:*">5</shiro:lacksPermission>
<!--这个标签表示缺少当前角色者可见-->
<shiro:lacksRole name="user">6</shiro:lacksRole>
<shiro:user>7</shiro:user>
<!--这个标签表示获取当前登录的用户-->
<shiro:principal></shiro:principal>
<ol>
    <shrio:hasRole name="admin">
        <li>
            <a href="https://www.google.com">谷歌</a>
        </li>
    </shrio:hasRole>
    <shiro:hasPermission name="product:*:01">
        <li>
            <a href="http://www.hugh-zhan9.xyz">个人博客</a>
        </li>
    </shiro:hasPermission>
    <li>
        <a href="https://www.baidu.com">baidu</a>
    </li>
</ol>
</body>
</html>
