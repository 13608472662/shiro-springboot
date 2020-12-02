<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div align="center">
    <div style="border: 3px #cad solid;width: 300px;height: 200px">
        <form method="post" action="${path}/user/login"><br>
            用户名:<input type="text" name="username"/><br><br>
            密&emsp;码:<input type="password" name="password"/><br><br>
            <input type="checkbox" name="rememberme" value="1">记住我7天</input><br><br>
            <input type="submit" value="登陆"/><br><br>
        </form>
    </div>
</div>
</body>
</html>