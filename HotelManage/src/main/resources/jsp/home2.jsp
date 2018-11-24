<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquerysession.js"></script>
    <script src="../js/servers.js"></script>
    <%--<script src="../js/Chart.js"></script>--%>

    <script src="../layui/layui.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>

<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">湖南大学教学服务系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" id="user_name"></li>
            <li class="layui-nav-item"><a href="../login.jsp">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="student_manage.jsp" target="right11">学生管理</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">成绩管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="grade_manage.jsp" target="right11">查看成绩</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe name="right11" width="100%" height="100%" scrolling="no"></iframe>
    </div>

    <div class="layui-footer" style="text-align: right">
        <!-- 底部固定区域 -->
        教学服务系统v1.0
    </div>
</div>

<script>
    //JavaScript代码区域
    $(function () {
        var userStr = $.session.get('user');
        var userJson = JSON.parse(userStr).tname;
        var user_name = $("#user_name");
        user_name.html(userJson);
    });

    layui.use('element', function () {
        var element = layui.element;
    });
</script>

</body>
</html>
<%--<style type="text/css">
    .top {
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        height: 60px;
        background: #333333;
        color: #fff;
    }

    .top-left-text {
        position: relative;
        top: 0px;
        left: 35px;
        bottom: 0px;
        line-height: 5px;
        font-size: 20px;
        color: #fff;
        float: left;
    }

    .left {
        position: absolute;
        top: 50px;
        left: 0;
        width: 140px;
        height: 100%;
        font-weight: 400;
    }

    .right {
        position: absolute;
        overflow: auto;
        left: 140px;
        top: 60px;
        width: 100%;
        height: 100%;
        background: #ffffff;
        min-width: 695px;
    }

    .menus1 {
        width: 140px;
        height: 80%;
        background: #20232b;
        color: #fff;
        border-right: 1px #0b0b13 solid;
    }

    .meus1_1 div {
        font-size: 20px;
    }

    a:link {
        color: lightseagreen;
        text-decoration: none;
    }

    a:visited {
        color: lightseagreen;
        text-decoration: none;
    }

    a:hover {
        color: green;
        text-decoration: underline;
    }

    .list {
        position: absolute;
        top: 18px;
        left: 0;
        width: 140px;
        height: 100%;
        font-weight: 400;
        border-bottom: solid 1px #316a91;
        margin: 40px auto 0 auto;
        background: #20232b;
        color: lightseagreen;
        border-right: 1px #0b0b13 solid;
    }

    .list ul li {
        border-bottom: 0;
    }

    .list ul li a {
        padding-left: 10px;
        color: lightseagreen;
        font-size: 12px;
        display: block;
        font-weight: bold;
        height: 36px;
        line-height: 36px;
        position: relative;
    }

    .list ul li ul {
        display: none;
    }

    .list ul li ul li {
        border-left: 0;
        border-right: 0;
    }

    iframe {
        border: 0;
    }
</style>--%>
<%--<div class="parent">
    <div class="top">
        <div class="top-left" style="padding-left: 15px;">
            <div class="fl" style="position: relative;top: 12px;">
                <img src="../dist/images/login/logo.jpg" width="30px" height="30px"></div>
            <div class="top-left-text">湖南大学教学服务系统</div>
        </div>
    </div>
    &lt;%&ndash;</div>&ndash;%&gt;
    <div class="list">
        <ul class="yiji">
            <li><a href="student_manage.jsp" target="right11">学生管理</a></li>
            <li><a href="#" class="inactive">成绩管理</a>
                <ul style="display: none">
                    <li><a href="grade_manage.jsp" target="right11" class="inactive active">查看成绩</a>
                    </li>
                </ul>
            </li>

        </ul>
    </div>
    <div class="right">
        <iframe name="right11" width="100%" height="100%" scrolling="no"></iframe>
    </div>
</div>--%>

<%--<script type="application/javascript">
    $(document).ready(function () {
        $('.inactive').click(function () {
            if ($(this).siblings('ul').css('display') == 'none') {
                $(this).parent('li').siblings('li').removeClass('inactives');
                $(this).addClass('inactives');
                $(this).siblings('ul').slideDown(100).children('li');
                if ($(this).parents('li').siblings('li').children('ul').css('display') == 'block') {
                    $(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
                    $(this).parents('li').siblings('li').children('ul').slideUp(100);
                }
            } else {
                //控制自身变成+号
                $(this).removeClass('inactives');
                //控制自身菜单下子菜单隐藏
                $(this).siblings('ul').slideUp(100);
                //控制自身子菜单变成+号
                $(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
                //控制自身菜单下子菜单隐藏
                $(this).siblings('ul').children('li').children('ul').slideUp(100);
                //控制同级菜单只保持一个是展开的（-号显示）
                $(this).siblings('ul').children('li').children('a').removeClass('inactives');
            }
        })
    });
</script>--%>
<%--<li class="last">--%>
<%--<a href="xuankejieguo.jsp" target="right11">更新成绩</a>--%>
<%--</li>--%>

<%--<li><a href="#" class="inactive">组织机构</a>--%>
<%--<ul style="display: none">--%>
<%--<li><a href="#" class="inactive active">美协机关</a>--%>
<%--<ul>--%>
<%--<li><a href="#">办公室</a></li>--%>
<%--<li><a href="#">人事处</a></li>--%>
<%--<li><a href="#">组联部</a></li>--%>
<%--<li><a href="#">外联部</a></li>--%>
<%--<li><a href="#">研究部</a></li>--%>
<%--<li><a href="#">维权办</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li><a href="#" class="inactive active">中国文联美术艺术中心</a>--%>
<%--<ul>--%>
<%--<li><a href="#">综合部</a></li>--%>
<%--<li><a href="#">大型活动部</a></li>--%>
<%--<li><a href="#">展览部</a></li>--%>
<%--<li><a href="#">艺委会工作部</a></li>--%>
<%--<li><a href="#">信息资源部</a></li>--%>
<%--<li><a href="#">双年展办公室</a></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--<li class="last"><a href="#">《美术》杂志社</a></li>--%>
<%--</ul>--%>
<%--</li>--%>

<%--<div class="left">--%>
<%--<div class="menus1" style="color:lightseagreen;min-height: 100%;overflow-y: auto;">--%>
<%--<div class="meus1_1">--%>

<%--<div id="cheng_ji">--%>
<%--<img src="../dist/images/nav/1.png" width="15px" height="15px" >&nbsp;&nbsp;--%>
<%--<a href="chengji.jsp" target="right11">学生成绩</a>--%>
<%--</div>--%>
<%--<div id="xuanke" class="layui-nav-item layui-nav-itemed">--%>

<%--<img src="../dist/images/nav/2.png" width="15px" height="15px">&nbsp;&nbsp;--%>
<%--<a href="" class="inactive">课程运行</a>--%>
<%--<dl class="layui-nav-child">--%>
<%--<dd><a href="xuanke.jsp" target="right11">选课</a></dd>--%>
<%--<dd><a href="xuankejieguo.jsp" target="right11">选课结果</a></dd>--%>
<%--</dl>--%>
<%--</div>--%>
<%--</div>--%>
<%--&lt;%&ndash;<div class="menus2" style="height:100%;overflow-y: auto;">&ndash;%&gt;--%>
<%--&lt;%&ndash;<div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<i class="fa fa-bars"></i><span style="color: #00A99D">&nbsp;&nbsp;菜单</span>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--&lt;%&ndash;<ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;<dl class="layui-nav-child" style="color: #00A99D">&ndash;%&gt;--%>
<%--&lt;%&ndash;&ndash;%&gt;--%>
<%--&lt;%&ndash;</dl>&ndash;%&gt;--%>
<%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--</div>--%>
<%--<div class="right" >--%>
<%--<iframe name="right11" width="100%" height="100%" scrolling = "no"></iframe>--%>
<%--</div>--%>