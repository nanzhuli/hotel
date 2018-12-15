var employeeEvent = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            employeeEvent.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.event.getByEmployee, {}, function (result) {
                var data = result.data;        //获取数据
                var $tbody = $("#employeeEvent-table").children('tbody');
                employeeEvent.funcs.renderHandler($tbody, data);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            employeeEvent.funcs.bindRefreshEventListener(refreshBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.eventno) + "</td>" +
                    "<td>" + home.vars.typeEvent[e.type] + "</td>" +
                    "<td>" + (e.roomno) + "</td>" +
                    "<td>" + (e.employno) + "</td>" +
                    "<td>" + home.funcs.spaceFunc(e.comment) + "</td>" +
                    "<td>" + home.funcs.timeStr(e.starttime) + "</td>" +
                    "<td><a href='#' class='deletes' id='delete-" + e.eventno + "'><i class=\"layui-icon layui-icon-ok\"></i></a></td>" +
                    "</tr>")
            });
            // 数据渲染完毕
            var deleteBtns = $('.deletes');
            employeeEvent.funcs.bindDeleteEventListener(deleteBtns);
        },
        bindRefreshEventListener: function (refreshBtn) {
            refreshBtn.off('click');
            refreshBtn.on('click', function () {
                layui.use('layer', function () {
                    var index = layer.load(2, {
                        offset: ['48%', '48%']
                    });
                    var time = setTimeout(function () {
                        layer.msg('刷新成功', {
                            offset: ['48%', '45%'],
                            time: 700
                        });
                        employeeEvent.init();
                        layer.close(index);
                        clearTimeout(time);
                    }, 200)
                });
            })
        },
        bindDeleteEventListener: function (deleteBtns) {
            deleteBtns.off('click');
            deleteBtns.on('click', function () {
                console.log("SUBMIT");
                var code = this.id.substr(7);
                console.log(code);
                layui.use('layer', function () {
                    layer.open({
                        type: 1,
                        title: '提交',
                        content: "<h5 style='text-align: center;padding-top: 8px'>确认完成？</h5>",
                        area: ['180px', '130px'],
                        btn: ['确认', '取消'],
                        offset: ['35%', '40%'],
                        yes: function (index) {
                            console.log(code);
                            $.post(home.urls.event.delete + code, {}, function (result) {
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        employeeEvent.init();
                                        clearTimeout(time)
                                    }, 500)
                                }
                                layer.close(index)
                            })
                        },
                        btn2: function (index) {
                            layer.close(index)
                        }
                    })
                })
            })
        }
    }
};