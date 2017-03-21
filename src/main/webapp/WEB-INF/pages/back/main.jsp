<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/css/semantic.min.css">
    <script src="/static/js/jquery.js" charset="UTF-8"></script>
    <script src="/static/js/semantic.min.js" charset="UTF-8"></script>
</head>
<body style="margin-top: 10px;">
<div class="ui grid">

    <%-- 标题 --%>
    <div class="row" style="background-color: #ffffff">
        <div class="sixteen wide column">
            <div class="ui secondary menu">
                <div class="item">JFourm 后台管理系统</div>
                <div class="right secondary menu">
                    <div class="item">${admin.userName}</div>
                    <a class="item">LogOut</a>
                </div>
            </div>
        </div>
    </div>
    <div class="ui divider" style="margin: 0px auto;padding: 0px auto;"></div>

    <div class="row">
        <div class="two wide column">
            <div class="ui vertical menu" style="margin-left: 10px;">
                <a class="item">
                    网站状态
                </a>
                <a class="item">
                    查看问题
                </a>
                <a class="item">
                    查看用户
                </a>
                <a class="item">
                    审核头条
                </a>
                <a class="item">
                    添加问题分类
                </a>
                <a class="item">
                    添加头条分类
                </a>
            </div>
        </div>
        <div class="fourteen wide column">
            <div id="frame">
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
                            <tr>
                                <td>Tom</td>
                                <td>tom@gmail.com</td>
                                <td>12</td>
                                <td>12</td>
                                <td>12</td>
                                <td>12</td>
                                <td>2017-01-01 08:00:00</td>
                                <td>
                                    <a class="ui label close" target="/user/closeUser.action?userId=${item.id}">禁用用户</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<script>
    $(function () {

        /* 菜单 */
        $(".ui.vertical.menu a").click(function () {
            var $this = $(this);
            var href = $this.attr("target");
            $.get(href, function(data){
                $("#frame").html(data);
            });
        });

        /* 禁用用户 */
        $(".ui.label.close").click(function () {
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
