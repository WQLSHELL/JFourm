<%--
  Created by IntelliJ IDEA.
  User: WuQinglong
  Date: 2017/3/10
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JFourm社区</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 5px;">

<div class="ui grid">

    <%@ include file="commons/header.jsp" %>

    <%-- 从 Request 中取值 --%>
    <!-- List 问题 -->
    <div class="row">
        <div class="three wide column"></div>
        <div class="seven wide column">

            <div class="ui card" style="width: 100%;">
                <div class="content">
                    <div class="header">${questionCategory.name}</div>
                </div>
                <div class="content">
                    ${questionCategory.description}
                </div>
            </div>

            <!-- 小导航 -->
            <div class="ui secondary pointing menu">
                <a href="/question/listLastCategoryQuestion.action?categoryId=${questionCategory.id}" class="item"
                   id="lastQuestion">最新提问</a>
                <a href="/question/listHotCategoryQuestion.action?categoryId=${questionCategory.id}" class="item"
                   id="hotQuestion">热门回答</a>
                <a href="/question/listNoCategoryQuestion.action?categoryId=${questionCategory.id}" class="item"
                   id="noQuestion">等待回答</a>
            </div>


            <!-- 问题列表 -->
            <c:forEach items="${requestScope.page.list}" var="item">
                <section>
                    <!-- 回答数 -->
                    <div style="width: 40px; height: 45px;text-align: center;background-color: #ff8171; color: white;float: left;margin: 5px 5px;">
                            ${item.answerNum} <br>
                        <small>回答</small>
                    </div>
                    <!-- 浏览量 -->
                    <div style="width: 40px; height: 45px;text-align: center;float: left;margin: 5px 5px;">
                            ${item.viewNum} <br>
                        <small>浏览</small>
                    </div>
                    <!-- 主要内容 -->
                    <div>
                        <!-- 提问者/回答者 -->
                        <span>${item.user.nickName}&nbsp;&nbsp;1分钟前回答</span>
                        <h5 style="margin: 10px 0px;"></h5>
                        <!-- 提问或者回答的问题 -->
                        <a href="/question/questionDetail.action?id=${item.id}"
                           style="color: #0f0f10; font-size: large;">
                                ${item.title}
                        </a>
                        <!-- 问题类别 -->
                        <label class="ui label">
                            <a href="" style="color: #0f0f10">
                                    ${item.category.name}
                            </a>
                        </label>
                    </div>
                </section>
                <div class="ui divider"></div>
            </c:forEach>

            <!-- 分页: 只能选择 首页, 上一页, 下一页, 末页 -->
            <div style="text-align: center;">
                <div class="ui buttons">
                    <c:if test="${not empty(page.totalPage)}">
                        <c:if test="${page.totalPage != 0}">
                            <c:if test="${page.hasPrev()}">
                                <a href="${href}?pageNo=1" style="color: #646465;">
                                    <button class="ui button">首页</button>
                                </a>
                                <a href="${href}?pageNo=${page.prevPageNo}" style="color: #646465;">
                                    <button class="ui button">上一页</button>
                                </a>
                            </c:if>
                            <button class="ui button disabled" style="color: black;">当前第${page.pageNo}页</button>
                            <c:if test="${page.hasNext()}">
                                <a href="${href}?pageNo=${page.nextPageNo}" style="color: #646465;">
                                    <button class="ui button">下一页</button>
                                </a>
                                <a href="${href}?pageNo=${page.totalPage}" style="color: #646465;">
                                    <button class="ui button">末页</button>
                                </a>
                            </c:if>
                        </c:if>
                    </c:if>
                </div>
            </div>

        </div>

        <%-- 从 Session 中取值 --%>
        <div class="three wide column">
            <!-- 最新头条 -->
            <div class="ui attached message" style="margin-top: 20px;">
                <div class="header" style="text-align: center;">
                    最新头条
                </div>
            </div>
            <div class="ui attached fluid segment">
                <c:forEach items="${headLines}" var="item">
                    <div style="margin-bottom: 5px;">
                        <a href="${item.url}">${item.title}</a>
                    </div>
                    <div class="ui divider"></div>
                </c:forEach>
            </div>

        </div>
        <div class="three wide column"></div>
    </div>

</div>
</body>
<script>

    $(function () {
        $('.ui.dropdown')
            .dropdown()
        ;

        var typeVal = "${type}";
        if (typeVal == "hot") {
            $("#hotQuestion").addClass("active");
        } else if (typeVal == "no") {
            $("#noQuestion").addClass("active");
        } else {
            $("#lastQuestion").addClass("active");
        }

    });

</script>
</html>
