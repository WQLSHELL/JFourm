<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body>

<div class="ui card" style="width: 98%;">
    <div class="content">
        <div class="header">用户列表</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <thead>
            <tr>
                <th>昵称</th>
                <th>邮箱</th>
                <th>问题数</th>
                <th>回答数</th>
                <th>头条数</th>
                <th>积分</th>
                <th>注册时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="item">
                <tr>
                    <td>${item.nickName}</td>
                    <td>${item.email}</td>
                    <td>${item.comments.size()}</td>
                    <td>${item.askedQuestions.size()}</td>
                    <td>${item.headLines.size()}</td>
                    <td>${item.score}</td>
                    <td><fmt:formatDate value="${item.registerTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td>
                        <a class="ui label close" target="/user/closeUser.action?userId=${item.id}">禁用用户</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
