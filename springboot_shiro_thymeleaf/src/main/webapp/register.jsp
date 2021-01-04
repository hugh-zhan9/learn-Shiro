<%--
  User: hugh_
  Date: 2021/1/1
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    用户名：<input type="tetx" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
