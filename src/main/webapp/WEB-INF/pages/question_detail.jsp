<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WuQinglong
  Date: 2017/3/11
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>问题明细</title>
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <link rel="stylesheet" href="/static/editormd/css/editormd.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
    <script src="/static/editormd/editormd.min.js" charset="UTF-8"></script>
</head>
<body>
<div class="ui grid">

    <%@ include file="commons/header.jsp"%>

    <%-- 该问题的 ID --%>
    <input type="hidden" id="id" value="${question.id}">

    <!-- 问题 -->
    <div class="row" style="background-color: #f6f6f6;">
        <div class="three wide column"></div>
        <!-- 问题明细 -->
        <div class="seven wide column">
            <div style="margin-top: 10px;">
                <!-- 问题标题 -->
                <span style="font-size: x-large">${question.title}</span> <br>
                <!-- 问题类别 -->
                <label class="ui label" style="margin: 10px 10px;">
                    <a href="/questionCategory/listAllQuestion.action?id=${question.category.id}"
                       style="color: #0f0f10">${question.category.name}</a>
                </label>
                <!-- 问题提问者 -->
                <a href="javascript:void(0);" style="margin: 10px 10px;">${question.user.nickName}</a>
                <!-- 提问时间 -->
                <span style="margin: 10px 10px;"><fmt:formatDate value="${question.submitTime}" pattern="yyyy-MM-dd" /></span>
            </div>

        </div>
        <!-- 收藏问题 -->
        <div class="three wide column">
            <div style="margin-top: 20px;">
                <a href="javascript:void(0);">
                    <button class="ui button" id="addFavorites">收藏</button>
                </a>
                &nbsp;&nbsp;
                <span style="font-size: medium">${question.attentionNum}</span> 关注
                &nbsp;&nbsp;
                <span style="font-size: medium">${question.viewNum}</span> 浏览
            </div>
        </div>
        <div class="three wide column"></div>
    </div>

    <!-- 问题描述及回答 -->
    <div class="row">
        <div class="three wide column"></div>
        <div class="seven wide column">

            <!-- 问题描述 -->
            <div>
                <p>${question.content}</p>
            </div>
            <div style="margin-top: 20px;">
                <span><fmt:formatDate value="${question.submitTime}" pattern="yyyy-MM-dd" /> 提问</span>
            </div>

            <div style="margin-top: 30px;font-size: large;">
                ${question.answerNum} 个评论
            </div>
            <hr>
            <!-- 评论 -->
             <div class="comments">

                <c:forEach items="${question.comments}" var="item">
                    <div class="comment">
                        <h3 class="ui header">
                            <img class="ui circular image" src="/static/image/user.png">
                            <div class="content">
                                ${item.user.nickName}
                            </div>
                        </h3>
                        <div class="ui segment">
                            <p>
                                ${item.content}
                            </p>
                        </div>
                        <div style="margin-left: 10px;">
                            <span><fmt:formatDate value="${item.submitTime}" pattern="yyyy-MM-dd" />回答</span>
                            <div class="ui labeled button right floated" tabindex="0">
                                <div class="ui button like" id="${item.id}">
                                    <i class="heart icon"></i> Like
                                </div>
                                <a class="ui basic label">
                                    ${item.likeNum}
                                </a>
                            </div>
                        </div>
                    </div>
                    <hr style="clear: both;background-color: #ffe7d1;border: none; height: 1px;margin-top: 30px;">
                </c:forEach>

            </div>

            <%-- 评论 --%>
            <div class="ui card" style="width: 100%;">
                <div class="content">
                    <div class="header">撰写答案</div>
                </div>
                <div class="content">
                    <div id="editormd">
                        <textarea style="display:none;"></textarea>
                    </div>
                    <button class="ui primary button right floated" id="submit">提交</button>
                </div>
            </div>

        </div>

        <!-- 侧边栏 -->
        <div class="three wide column">
            <div class="ui attached message" style="margin-top: 20px;">
                <div class="header" style="text-align: center;">
                    最新头条
                </div>
            </div>
            <div class="ui form attached fluid segment">
                <div style="margin-bottom: 5px;">
                    <a href="">NodeJS 轻量级内存监控工具</a>
                </div>
                <hr>
                <div style="margin-bottom: 5px;">
                    <a href="">RCurl并行发送多个请求导致内存增长的解决方法</a>
                </div>
                <hr>
                <div style="margin-bottom: 5px;">
                    <a href="">机器学习、深度学习与自然语言处理领域推荐的书籍列表 - 知乎专栏</a>
                </div>
                <hr>
            </div>
        </div>
        <div class="three wide column"></div>
    </div>

</div>
</body>
<script>
    var testEditor; // 声明MD编辑器
    $(function () {

        /* 评论点赞 */
        $(".ui.button.like").click(function () {
            var $this = $(this);
            var idVal = $this.attr("id");
            $.ajax({
                url: "/comment/likeComment.action",
                type: "post",
                data: {"id":idVal},
                success: function () {
                    /* TODO 处理一个用户只能赞一次 */
                    var likeNum = $this.next().html();
                    $this.next().html(parseInt(likeNum) + 1);
                }
            });

        });

        /* 进行评论 */
        $("#submit").click(function () {
            var contentVal = testEditor.getHTML();
            var idVal = $("#id").val();
            $.ajax({
                url: "/comment/addComment.action",
                type: "post",
                data: {"questionId":idVal, "content": contentVal},
                success: function (result) {
                    alert(result.message);
                    window.location.reload(); // 刷新页面
                }
            });
        });

        /* 添加收藏 */
        $("#addFavorites").click(function () {
            var idVal = $("#id").val();
            $.ajax({
                url: "/user/favoriteQuestion.action",
                type: "post",
                data: {"questionId":idVal},
                success: function (result) {
                    if (result.status == "true") {
                        $("#addFavorites").html("已收藏");
                    }
                }
            });
        });


        /* MarkDown 编辑器 */
        testEditor = editormd({
            id: "editormd",
            width: "100%",
            height: 640,
            path: "../../static/editormd/lib/",
            placeholder: "详细描述问题内容",
            watch : false,
            toolbarIcons: function () {
                return [
                    "undo", "redo", "|",
                    "bold", "del", "italic", "quote", "uppercase", "lowercase", "|",
                    "h1", "h2", "h3", "h4", "h5", "h6", "|",
                    "list-ul", "list-ol", "hr", "|",
                    "watch", "fullscreen"
                ]
            }
        });
    });
</script>
</html>