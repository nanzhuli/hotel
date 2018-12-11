<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
    .thehead {
        position: absolute;
        width: 348px;
        height: 47px;
        line-height: 53px;
    }

    .box {
        position: absolute;
        left: 64%;
        top: 30%;
        width: 348px;
        height: 307px;
        background-color: white;
        border-radius: 0.3em;
        border: solid #666666 0.5px;
    }

    .touch {
        padding-bottom: 11px;
        margin-left: 70px;
        font-family: 'PingFangSC-Regular', 'PingFang SC';
        font-weight: 400;
        font-style: normal;
        font-size: 16px;
        cursor: pointer;
    }

    .touch.selected {
        border-bottom: rgb(0, 169, 157) solid 2px;
        color: rgb(0, 169, 157);
    }

    .users {
        position: absolute;
        width: 272px;
        height: 36px;
        margin-top: 90px;
        margin-left: 38px;
        border-radius: 0.2em;
        border: solid #aaa 0.5px
    }

    .pass {
        position: absolute;
        width: 272px;
        height: 36px;
        margin-top: 160px;
        margin-left: 38px;
        border-radius: 0.2em;
        border: solid #aaa 0.5px
    }

    .theicon {
        position: absolute;
        margin-top: 10px;
        margin-right: 10px;
        width: 20px;
        height: 20px;
        opacity: 0.8;
    }

    .thestyle {
        margin-top: 3px;
        margin-left: 10px;
        width: 233px;
        height: 30px;
        border: 0px;
        outline: none;
    }

    .button {
        position: absolute;
        background-color: #00A99D;
        margin-left: 38px;
        margin-top: 240px;
        text-align: center;
        line-height: 30px;
        width: 272px;
        height: 30px;
        font-family: '微软雅黑 Regular', '微软雅黑';
        font-weight: 400;
        font-style: normal;
        font-size: 14px;
        color: #FFFFFF;
        border-radius: 0.2em;
        border: 0px;
        outline: none;
        cursor: pointer;
    }

    .layui-layer-content {
        height: 80px !important;
    }
</style>
<body style=" background-repeat:no-repeat; background-size:100% 100%; background-attachment: fixed;"
      background="./dist/images/login/bg_900.jpg">
<div id="logo" style="">
    <div>
        <div id="logo-img" style="float: left">
            <img src="./dist/images/login/logo_trans.png" style="height: 80px; width: 80px"/>
        </div>
        <div style="position: relative; font-size: 25px ;top: 25px;">
            <span class="cn">&nbsp;&nbsp;湖南大学教学服务系统</span><br>
        </div>
    </div>
</div>
<div class="box">
    <div class="thehead">
        <span id="staff" class="touch">学生登陆</span>
        <span id="client" class="touch">教师登陆</span>
    </div>
    <div id="staff_login">
        <span id="error_box" class="tip1"></span>
        <div class="users">
            <input id="code" class="thestyle" type="text" value tabindex="0" placeholder="请输入学号"/>
            <img class="theicon" src="./dist/images/login/user.png"/>
        </div>
        <div class="pass">
            <input id="password" class="thestyle" type="password" value maxlength="12" placeholder="请输入密码"/>
            <img class="theicon" src="./dist/images/login/pass.png"/>
        </div>
        <input id="register1" class="button" type="submit" value="登录"/>
    </div>
    <div id="client_login">
        <span id="error_box2" class="tip1"></span>
        <div class="users">
            <input id="code2" class="thestyle" type="text" value tabindex="0" placeholder="请输入工号"/>
            <img class="theicon" src="./dist/images/login/user.png"/>
        </div>
        <div class="pass">
            <input id="password2" class="thestyle" type="password" value maxlength="12" placeholder="请输入密码"/>
            <img class="theicon" src="./dist/images/login/pass.png"/>
        </div>
        <input id="register2" class="button" type="submit" value="登录"/>
    </div>
</div>
<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jquerysession.js"></script>
<script src="./js/servers.js"></script>
<script type="text/javascript" src="./layui/layui.js"></script>
<link rel="stylesheet" href="./css/style.css" media="all">

<script type="text/javascript">
    $(function () {
        /** 登录组件的一些逻辑 */
        $('#staff').addClass('selected');
        $('#client_login').hide();
        $('#staff_login').show();

        $('#staff').click(function () {
            $('#client').removeClass('selected');
            $('#staff').addClass('selected');
            $('#client_login').hide();
            $('#staff_login').show();
        });

        $('#client').click(function () {
            $('#staff').removeClass('selected');
            $('#client').addClass('selected');
            $('#staff_login').hide();
            $('#client_login').show();
        })

        $("#register1").on('click', function () {
            var username = $("#code").val()
            var password = $("#password").val()
            // $.post(servers.backup() + "student/login", {code: username, password: password}, function (Result) {
                // var resCode = Result.code
                var resCode = 0;
                console.log(resCode)
                if (resCode == 0) {
                    console.log(result)
                    $.session.set('user', JSON.stringify(result.data));
                    document.location = './jsp/home1.jsp';
                }
                else {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.open({
                            type: 1,
                            content: "<div class='align_middle'>" + result.message + "</div>",
                            area: ['280px', '180px'],
                            btn: ['关闭'],
                            offset: 'auto', // ['10%', '40%'],
                            btnAlign: 'c',
                            yes: function () {
                                layer.closeAll();
                            }
                        });
                    })
                }
            // })
        })
        $("#register2").on('click', function () {
            var username = $("#code2").val()
            var password = $("#password2").val()
            $.post(servers.backup() + "teacher/login", {code: username, password: password}, function (result) {
                var resCode = result.code
                console.log(resCode)
                if (resCode == 0) {
                    console.log(result)
                    $.session.set('user', JSON.stringify(result.data));
                    document.location = './jsp/home2.jsp';
                }
                else {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.open({
                            type: 1,
                            content: "<div class='align_middle'>" + result.message + "</div>",
                            area: ['280px', '180px'],
                            btn: ['关闭'],
                            offset: 'auto', // ['10%', '40%'],
                            btnAlign: 'c',
                            yes: function () {
                                layer.closeAll();
                            }
                        });
                    })
                }
            })
        })

//        /** 获取所有的权限 */
//        $.get(servers.backup() + 'modelOperation/getAll', {}, function (Result) {
//            var modelOperations = Result.data
//            window.localStorage.setItem('modelOperations', JSON.stringify(modelOperations))
//        })
//        $.get(servers.backup() + 'role/getAllRoleModelOperation', {}, function (Result) {
//            var roleModelOperation = Result.data
//            window.localStorage.setItem('roleModelOperation', JSON.stringify(roleModelOperation))
//        })
//        /** 获取所有的operations并用远足存储起来 */
//        $.get(servers.backup() + 'operation/getAll', {}, function (Result) {
//            var operations = Result.data.sort(function (a, b) {
//                return a.code - b.code
//            })
//            window.localStorage.setItem('operations', JSON.stringify(operations))
//        })
    })
</script>
</body>
</html>