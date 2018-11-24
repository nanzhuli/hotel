<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquerysession.js"></script>
    <script type="text/javascript" src="../js/servers.js"></script>
    <script src="../layui/layui.js"></script>

    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/style.css" media="all">
</head>

<body>
<table class="layui-table" id="equipment_table" style="width: 70%;" border="1px">
    <thead>
    <tr>
        <%--<td>序号</td>--%>
        <td>学号</td>
        <td>姓名</td>
        <td>课程名称</td>
        <td>教师</td>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
<script type="application/javascript">
    $(function () {
        //var userStr = $.session.get('user')
        var userStr = $.session.get('user')
//        if (!userStr) {
//            console.log('用户已经失去登录，请重新登录')
//            document.location = '../login.jsp';
//        }
        var userJson = JSON.parse(userStr).sno
        var name = JSON.parse(userStr).sname
        console.log(userJson)
        $.post(servers.backup() + "studentCourse/selectedCourse", {sno: userJson}, function (result) {
            console.log(result)
            if (result.code == 0) {
                result.data.forEach(function (e) {
                    var page = e
                    $('#equipment_table').append("<tr>" +
                        "<td>" + (page.student.sno) + "</td>" +
                        "<td>" + (page.student.sname) + "</td>" +
                        "<td>" + (page.course.cname) + "</td>" +
                        "<td>" + (page.course.teacher.tname) + "</td>" +
                        "<tr>")
                })

            }

        })

    })
</script>
</body>
</html>