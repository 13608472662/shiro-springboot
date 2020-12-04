<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

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
    <h1>欢迎来到主页面</h1>
    <div align="right">

        <%--认证成功展示 主体必须调用login方法才算认证成功--%>
        <%-- <shiro:authenticated>
             <div>
                 欢迎:<span style="color: dodgerblue"><shiro:principal/></span>来到后台管理系统
                 <a href="${path}/logout">退出</a>
             </div>
         </shiro:authenticated>
         &lt;%&ndash;没有认证成功展示&ndash;%&gt;
         <shiro:notAuthenticated>
             <div>
                 如果您想浏览更多信息请<a href="${path}/user/login.jsp">登录</a>
             </div>
         </shiro:notAuthenticated>--%>

        <%--认证成功展示的内容  记住我登陆也算认证成功--%>
        <shiro:user>
            <div>
                欢迎:<span style="color: dodgerblue"><shiro:principal/></span>来到后台管理系统
                <a href="${path}/logout">退出</a>
            </div>
            <div align="left">


                <!--判断当前主体有这些其中一个角色就展示-->
                <shiro:hasAnyRoles name="admin,superAdmin">
                    <button>用户管理</button>
                    <br><br>
                    <!--判断当前主体有该权限时展示-->
                    <shiro:hasPermission name="user:add">
                        <button>增</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete">
                        <button>删</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update">
                        <button>改</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:select">
                        <button>查</button>
                    </shiro:hasPermission><br><br>
                    <button>类别管理</button>
                    <br><br>
                    <button><a href="${path}/user/add">添加类别</a></button>
                    <button>视频管理</button>
                    <br><br>
                    <button>反馈管理</button>
                    <br><br>
                </shiro:hasAnyRoles>

                <!--判断当前主体是否由该角色-->
                <shiro:hasRole name="superAdmin">
                    <button>日志管理管理</button>
                    <br><br>
                    <button>管理员管理</button>
                    <br><br>
                    <shiro:hasPermission name="admin:add">
                        <button>增</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="admin:delete">
                        <button>删</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="admin:update">
                        <button>改</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="admin:query">
                        <button>查</button>
                    </shiro:hasPermission><br><br>
                </shiro:hasRole>

                <!--判断当前主体没有该角色-->
                <shiro:lacksRole name="super">
                    <button>没有super角色展示</button>
                    <br><br>
                </shiro:lacksRole>


                <!--判断当前主体没有有该权限时展示-->
                    <%--<shiro:lacksPermission name=""></shiro:lacksPermission>--%>

            </div>
        </shiro:user>
        <%--没有认证成功展示的内容--%>
        <shiro:guest>
            <div>
                如果您想浏览更多信息请<a href="${path}/user/login.jsp">登录</a>
            </div>
        </shiro:guest>


    </div>
</div>

</body>
</html>