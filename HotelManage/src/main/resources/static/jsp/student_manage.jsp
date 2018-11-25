<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
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

    <style>
        .layui-layer-content{
            height: 80px !important;
        }
    </style>
</head>

<body>
<%--<div class="whitespace"></div>--%>
<table class="layui-table" id="equipment_table" style="width: 70%;" border="1px">
    <thead>
    <tr>
        <%--<td>序号</td>--%>
        <td>学号</td>
        <td>姓名</td>
        <td>专业</td>
        <td>编辑</td>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<script type="application/javascript">

    $(function () {
        init()
    });

    function init() {

        var userStr = $.session.get('user')
        var userJson = JSON.parse(userStr).tno;
        console.log(userJson);
        $.post(servers.backup() + "studentCourse/getAllGrade", {tno: userJson}, function (result) {
            if (result.code == 0) {
                var $tbody = $("#equipment_table").children('tbody')
                $tbody.empty();
                result.data.forEach(function (e) {
                    var page = e;
                    $('#equipment_table').append("<tr>" +
                        "<td>" + (page.student.sno) + "</td>" +
                        "<td>" + (page.student.sname) + "</td>" +
                        "<td>" + (page.student.department.dname) + "</td>" +
                        "<td><a href='#' class='checkbox' id='choose" + page.scno + "'><i class='layui-icon'>&#xe640;</i></a></td>" +
                        "<tr>")
                });
                var editBtns = $('.checkbox');
                bindEditEventListener(editBtns)
            }

        })

    }

    function bindEditEventListener(editBtns) {
        editBtns.off('click');
        editBtns.on('click', function () {
            var _selfBtn = $(this);
            var code = _selfBtn.attr('id').substr(6);
            console.log(code);

            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    type: 1,
                    content: "<div class='align_middle'>确认要删除该学生？</div>",
                    area: ['280px', '180px'],
                    btn: ['确定', '取消'],
                    // offset: 'auto', // ['10%', '40%'],
                    btnAlign: 'c',
                    yes: function (index) {
                        $.post(servers.backup() + "studentCourse/delete", {scno: code}, function (e) {
                            console.log(e);
                            if (e.code == 0) {
                                console.log("成功" + code);
                                layer.close(index);
                                layer.open({
                                    type: 1,
                                    content: "<div class='align_middle'>删除成功</div>",
                                    area: ['280px', '180px'],
                                    btn: ['关闭'],
                                    offset: 'auto', // ['10%', '40%'],
                                    btnAlign: 'c',
                                    yes: function () {
                                        layer.closeAll();
                                        init();
                                    }
                                });
                            }
                            else {
                                console.log("失败" + e.message);
                                layer.close(index);
                                layer.open({
                                    type: 1,
                                    content: "<div class='align_middle'>失败<br>" + e.message + "</div>",
                                    area: ['280px', '180px'],
                                    btn: ['关闭'],
                                    offset: 'auto', // ['43%', '49%'],
                                    btnAlign: 'c',
                                    yes: function () {
                                        layer.closeAll();

                                    }
                                });
                            }
                        })
                    },
                    btn2: function (index) {
                        layer.close(index)
                    }
                })
            });
        })
    }
</script>
</body>
</html>

<%--
//var userStr = $.session.get('user')
// if (!userStr) {
//     console.log('用户已经失去登录，请重新登录')
//     document.location = '../login.jsp';
// }
--%>
