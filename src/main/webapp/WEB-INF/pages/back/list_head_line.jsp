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
        <div class="header">未审核头条</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <thead>
            <tr>
                <th>标题</th>
                <th>作者</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><a href="">头条标题</a></td>
                <td>Tom猫</td>
                <td>2017-01-01</td>
                <td>
                    <a class="ui label review" target="/headline/reviewPassed.action?headLineId=12">通过</a>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {

        /* 审核头条 */
        $(".ui.label.review").click(function () {
            var $this = $(this);
            var href = $this.attr("target");
            $.get(href, function () {
                $this.parent().parens().hide();
            });
            return false;
        });

    });
</script>
</body>
</html>
