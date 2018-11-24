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

    <style>
        .layui-layer-content{
            height: 80px !important;
        }
    </style>
</head>

<body>
<table class="layui-table" id="equipment_table" style="width: 70%;" border="1px">
    <thead>
    <tr>
        <%--<td>序号</td>--%>
        <td>课程号</td>
        <td>课程名称</td>
        <td>教师</td>
        <td class="edit_td">选择</td>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<script type="application/javascript">
    $(function () {
        init()
    })

    function init() {
        //var userStr = $.session.get('user')
        var userStr = $.session.get('user')
        var userJson = JSON.parse(userStr).sno

        console.log(userJson)
        $.post(servers.backup() + "departmentCourse/canSelect", {sno: userJson}, function (result) {
            console.log(result)
            if (result.code == 0) {
                result.data.forEach(function (e) {
                    var page = e
                    $('#equipment_table').append("<tr class='new'>" +
                        "<td>" + (page.cno) + "</td>" +
                        "<td>" + (page.cname) + "</td>" +
                        "<td>" + (page.teacher.tname) + "</td>" +
                        "<td><a href='#' class='checkbox' id='choose" + page.cno + "'><i class='layui-icon'>&#xe605;</i></a></td>" +
                        "<tr>")
                })

                var editBtns = $('.checkbox')
                bindEditEventListener(editBtns)
            }

        })

    }

    function bindEditEventListener(editBtns) {
        var userStr = $.session.get('user')
        var userJson = JSON.parse(userStr).sno
        editBtns.off('click')
        editBtns.on('click', function () {
            var _selfBtn = $(this)
            var cno = _selfBtn.attr('id').substr(6)

            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    type: 1,
                    content: "<div class='align_middle'>确认选择该课程？</div>",
                    area: ['280px', '180px'],
                    btn: ['确定', '取消'],
                    // offset: 'auto', // ['10%', '40%'],
                    btnAlign: 'c',
                    yes: function (index) {
                        $.post(servers.backup() + "studentCourse/select", {sno: userJson, cno: cno}, function (e) {
                            console.log(e);
                            if (e.code == 0) {
                                console.log("成功" + cno);
                                layer.close(index);
                                layer.open({
                                    type: 1,
                                    content: "<div class='align_middle'>选课成功</div>",
                                    area: ['280px', '180px'],
                                    btn: ['关闭'],
                                    offset: 'auto', // ['10%', '40%'],
                                    btnAlign: 'c',
                                    yes: function () {
                                        $('.new').remove();
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
            //
            // $.post(servers.backup() + "studentCourse/select", {sno: userJson, cno: cno}, function (e) {
            //     if (e.code === 0) {
            //         layui.use('layer', function () {
            //             var layer = layui.layer;
            //             layer.msg(e.message)
            //             $('.new').remove()
            //             init()
            //         })
            //     }
            // })
        })
    }
</script>
</body>
</html>