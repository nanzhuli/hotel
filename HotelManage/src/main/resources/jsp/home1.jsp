<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquerysession.js"></script>
    <script src="../js/servers.js"></script>
    <script src="../layui/layui.js"></script>
    <link rel="stylesheet" href="../layui/css/layui.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">宾馆管理系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" id="user_name"></li>
            <li class="layui-nav-item"><a href="../login.jsp">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a href="javascript:void(0)">客房</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:void(0)" onclick="jump('roomStd')">客房标准</a></dd>
                        <dd><a href="javascript:void(0)" onclick="jump('roomInfo')">房间信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)" onclick="jump('order')">订单</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)">员工</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:void(0)" onclick="jump('employeeEvent')">员工事务</a></dd>
                        <dd><a href="javascript:void(0)" onclick="jump('employeeInfo')">员工信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)" onclick="jump('event')">事务</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)" onclick="jump('garage')">停车</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)" onclick="jump('finance')">财务</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)">历史</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:void(0)" onclick="jump('orderHistory')">订单历史</a></dd>
                        <dd><a href="javascript:void(0)" onclick="jump('garageHistory')">停车历史</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:void(0)" onclick="jump('member')">会员</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="myFrame" name="right11" width="100%" height="100%" scrolling="no"></iframe>
    </div>

    <div class="layui-footer" style="text-align: right">
        <!-- 底部固定区域 -->
        HMS系统 BETA
    </div>
</div>

<script>
    // layui 初始化
    layui.use('element', function () {
        var element = layui.element;
    });

    function jump(type) {
        $("#myFrame").attr("src", "../src/html/" + type + ".html");
    }

    // //JavaScript代码区域
    // $(function () {
    //     var userStr = $.session.get('user');
    //     var userJson = JSON.parse(userStr).sname;
    //     var user_name = $("#user_name");
    //     user_name.html(userJson);
    // });
</script>

</body>
</html>