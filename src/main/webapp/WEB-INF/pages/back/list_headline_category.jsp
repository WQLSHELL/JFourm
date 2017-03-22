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
        <div class="header">列出头条分类</div>
    </div>
    <div class="content">
        <table class="ui table">
            <thead>
            <tr>
                <th>序号</th>
                <th>分类名称</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Java</td>
                <td><a href="javascript:alert('暂未实现该功能.')">修改</a></td>
            </tr>
            <tr>
                <td>2</td>
                <td>MySQL</td>
                <td><a href="javascript:alert('暂未实现该功能.')">修改</a></td>
            </tr><tr>
                <td>3</td>
                <td>J2EE</td>
                <td><a href="javascript:alert('暂未实现该功能.')">修改</a></td>
            </tr>

            <%--<c:forEach items="${}" var="item">--%>
            <%--</c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
