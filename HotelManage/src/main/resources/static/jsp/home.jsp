<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
    <link href="../fontawesome/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/home.css">
    <link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>
<%--Hello world--%>
<body>
<div class="parent">
    <div class="top">
        <div class="top-left" style="padding-left: 12px;">
            <div class="fl" style="position: relative;top: 12px;"><img src="../dist/images/login/logo.png" width="30px"
                                                                       height="30px"></div>
            <div class="top-left-text">湖南大学教学服务系统</div>
        </div>
        <div class="fr vertical_center" style="height: auto;">
            <div class="vertical_center fl" style="height: 100%;border-left: 1px #ffffff dotted;">
                <div style="padding: 0 13px 0 13px;">
                    <a class="text_center" href="#" style="color: white;font-size: 22px;">
                        <i class="fa fa-cog"></i>
                    </a>
                </div>
            </div>
            <div style="border-left: 1px #ffffff dotted;border-right: 1px #ffffff dotted;" class="fl">
                <div style="padding: 0 10px 0 10px;height: 100%;" class="vertical_center">
                    <img src="https://tvax2.sinaimg.cn/crop.0.0.512.512.180/69e273f8ly8fr3qffljhwj20e80e8t90.jpg"
                         alt=""
                         width="45" height="45"
                         style="border-radius: 50%;"/>
                    <span>&nbsp;管理员</span>
                </div>
            </div>
            <div class="vertical_center fl" style="height: 100%;">
                <div style="padding: 0 13px 0 13px;">
                    <a href="#" id="exit" style="color: white;font-size: 22px;">
                        <i class="fa fa-sign-out"></i>
                    </a>
                </div>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <div class="left">
        <div class="menus1" style="min-height: 100%;overflow-y: auto;">
            <ul>

            </ul>
        </div>
        <div class="menus2" style="height:100%;overflow-y: auto;">
            <div>
                <i class="fa fa-bars"></i><span>&nbsp;&nbsp;菜单</span>
            </div>
            <ul>
            </ul>
        </div>
    </div>
    <div class="right" style="max-height: 100%;overflow-y: scroll;">

    </div>
</div>

<script src="../js/jquery.min.js"></script>

<script src="../layui/layui.all.js" charset="utf-8"></script>
<script src="../layer/layer/layer.js"></script>
<script src="../js/jquerysession.js"></script>

<script src="../js/servers.js"></script>
<script src="../src/js/home.js"></script>
<script src="../js/Chart.js"></script>
<script type="application/javascript">
    $(function () {

        (function () {
            Date.prototype.Format = function (fmt) { //author: meizz
                var o = {
                    "M+": this.getMonth() + 1, //月份
                    "d+": this.getDate(), //日
                    "h+": this.getHours(), //小时
                    "m+": this.getMinutes(), //分
                    "s+": this.getSeconds(), //秒
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                    "S": this.getMilliseconds() //毫秒
                };
                if (/(y+)/.test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                }
                for (var k in o)
                    if (new RegExp("(" + k + ")").test(fmt))
                        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;

            }
        })()

        function createVersion() {
            var date = new Date();
            return "<span>v</span>4.275." + ( date.getMonth() + 1) + date.getDate() + ".BETA";
        }

        /** 日期显示 */
        $('.top-left-text')[0].innerHTML = "<span>长远锂科MES系统&nbsp;<strong id='version' style='color: #1E9FFF;font-size:13px; font-weight: 600;'>&nbsp;" + createVersion() + "</strong></span>"
        var userStr = $.session.get('user')
        if (!userStr) {
            console.log('用户已经失去登录，请重新登录')
            document.location = '../login.jsp';
        }
        var userJson = JSON.parse(userStr)
        var menu1Wrapper = $('.menus1 ul')
        var menu2Wrapper = $('.menus2 ul')
        home.init(userJson, menu1Wrapper, menu2Wrapper)
    })

</script>
</body>
</html>
