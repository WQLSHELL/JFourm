<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WuQinglong
  Date: 2017/3/10
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>定制我的标签</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 5px;">

<div class="ui grid">

    <%@ include file="commons/header.jsp" %>

    <div class="row">
        <div class="three wide column"></div>
        <div class="ten wide column">
            <div style="margin-bottom: 10px;">
                <h2>
                    类别 <br>
                    <small>类别是最有效的内容组织形式，正确的使用标签能更快的发现和解决你的问题</small>
                </h2>
            </div>
        </div>
        <div class="three wide column"></div>
    </div>

    <!-- 我关注的问题标签 -->
    <c:if test="${not empty(sessionScope.user)}">
    <div class="row">
        <div class="three wide column"></div>
        <div class="ten wide column">
            <div style="margin-bottom: 10px;" id="myTag">
                <h4>我关注的</h4>
            </div>
            <c:forEach items="${userQuestionCategories}" var="item">
                <div class="ui attached message">
                    <div class="ui image label">${item.name} <i class="delete icon" id="${item.id}"></i></div>
                </div>
            </c:forEach>

        </div>
        <div class="three wide column"></div>
    </div>
    </c:if>

    <!-- 这里显示我没有关注的标签 -->
    <div class="row">
        <div class="three wide column"></div>
        <div class="twelve wide column">
            <div class="ui cards">

                <c:forEach items="${questionCategories}" var="item">
                    <div class="card">
                        <div class="content">
                                <%-- TODO 列出该分类下的所有问题 --%>
                            <div class="header"><a href="">${item.name}</a></div>
                            <div class="description">
                                    ${item.description}
                            </div>
                        </div>
                        <a href="" class="attention" id="${item.id}" name="${item.name}">
                            <div class="ui bottom attached button">
                                <i class="add icon"></i>
                                加关注
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
        </div>
        <div class="two wide column"></div>
    </div>

</body>
<script>
    $(function () {
        /* TODO 击关注事件 */
        $(".attention").click(function () {
            if ("${login}" == "false") {
                alert("请登录之后再关注.");
                return false;
            }
            var $this = $(this);
            var idVal = $this.attr("id");
            var nameVal = $this.attr("name");
            $.ajax({
                url: "/user/attention/addQuestionCategory.action",
                type: "post",
                data: {"id": idVal},
                success: function () {
                    alert("关注成功.");
                    $this.children("div").addClass("disabled");
                    $("#myTag").insertAfter("<div class='ui attached message'><div class='ui image label'>" +
                        nameVal + " <i class='delete icon' id='" + idVal + "'></i></div> </div>");
                }
            });
            return false;
        });
        /* TODO 点击删除事件 */
        $(".delete.icon").click(function () {
            if ("${login}" == "false") {
                alert("请登录之后再关注.");
                return false;
            }
            var $this = $(this);
            var idVal = $this.attr("id");
            $.ajax({
                url: "/user/attention/deleteQuestionCategory.action",
                type: "post",
                data: {"id": idVal},
                success: function () {
                    alert("删除成功.");
                    $this.parent().hide();
                }
            });
            return false;
        });
    });
</script>
</html>
