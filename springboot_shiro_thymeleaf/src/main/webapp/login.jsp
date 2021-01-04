<%--
  Created by IntelliJ IDEA.
  User: hugh_
  Date: 2020/8/30
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名：<input type="tetx" name="username"><br>
    密码：<input type="text" name="password"><br>
    验证码：<input type="text" name="code"><img src="user/getImage"/><br>
    <input type="submit" value="登录">
</form>

</body>
</html>
