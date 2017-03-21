<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body>

<div class="ui card" style="width: 100%;">

    <div class="content">
        <div class="header">我的评论</div>
    </div>
    <div class="content">
        <table class="ui celled table">
            <tbody>
                <c:forEach items="${page.list}" var="item">
                    <tr>
                        <td><span class="ui label">${item.question.answerNum} 回答</span></td>
                        <td><a href="">${item.question.title}</a></td>
                        <td>${item.question.submitTime}</td>
                        <td><a class="ui label delete" target="/comment/deleteComment.action?commentId=${item.id}">删除评论</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(function () {
        
        /* 删除评论 */
        $(".ui.label.delete").click(function () {
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
