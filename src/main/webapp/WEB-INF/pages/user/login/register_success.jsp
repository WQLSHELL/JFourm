<%--
  Created by IntelliJ IDEA.
  User: WuQinglong
  Date: 2017/3/10
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body>
<div class="ui grid">

    <%@ include file="../../commons/header.jsp"%>

    <div class="row" style="margin-top: 200px;">

        <div class="four wide column"></div>
        <div class="three wide column"></div>
        <div class="two wide column">
            <div class="ui attached message">
                注册成功, 请前往邮箱激活账号.
            </div>
        </div>
        <div class="three wide column"></div>
        <div class="four wide column"></div>
    </div>

</div>
</body>
</html>