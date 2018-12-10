var employeeInfo = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            employeeInfo.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.employee.getAll, {}, function (result) {

                var data = result.data;        //获取数据
                var $tbody = $("#employee-info-table").children('tbody');
                employeeInfo.funcs.renderHandler($tbody, data);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            employeeInfo.funcs.bindRefreshEventListener(refreshBtn);
            var addBtn = $("#add");
            employeeInfo.funcs.bindAddEventListener(addBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.employno) + "</td>" +
                    "<td>" + (e.employname) + "</td>" +
                    "<td>" + home.vars.gender[e.employsex] + "</td>" +
                    "<td>" + (e.employage) + "</td>" +
                    "<td>" + home.vars.position[e.employposition] + "</td>" +
                    "<td>" + home.vars.authority[e.employauthority] + "</td>" +
                    "<td>" + (e.employpaymentpermonth) + "</td>" +
                    "<td>" + home.vars.workTime[e.employworktime] + "</td>" +
                    "<td><a href='#' class='edits' id='edit-" + e.employno + "'><i class=\"layui-icon layui-icon-edit\"></i></a></td>" +
                    "<td><a href='#' class='deletes' id='delete-" + e.employno + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                    "</tr>")
            });
            // 数据渲染完毕
            var editBtns = $('.edits');
            var deleteBtns = $('.deletes');
            employeeInfo.funcs.bindEditEventListener(editBtns);
            employeeInfo.funcs.bindDeleteEventListener(deleteBtns);
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
                        employeeInfo.init();
                        layer.close(index);
                        clearTimeout(time);
                    }, 200)
                });
            })
        },
        // 要做个输入合法性检测
        bindAddEventListener: function (addBtn) {
            addBtn.off('click');
            addBtn.on('click', function () {
                console.log("ADD");
                // 弹出框
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.open({
                        type: 1,
                        title: '添加',
                        content:
                            "<div id='addModal' class='popup'>" +
                            "<p>工号：<input type='number' id='employee-no'/></p>" +
                            "<p>姓名：<input type='text' id='employee-name'/></p>" +
                            "<p>性别：<select id='employee-sex'>" +
                            home.funcs.generateSelect(2, home.vars.gender) +
                            "</select></p>" +
                            "<p>年龄：<input type='number' id='employee-age'/></p>" +
                            "<p>职位：<select id='employee-position'>" +
                            home.funcs.generateSelect(4, home.vars.position) +
                            "</select></p>" +
                            "<p>权限：<select id='employee-authority'>" +
                            home.funcs.generateSelect(3,home.vars.authority)+
                            "</select></p>" +
                            "<p>工资：<input type='number' id='employee-salary'/></p>" +
                            "<p>时段：<select id='employee-time'>" +
                            home.funcs.generateSelect(3, home.vars.workTime) +
                            "</select></p>" +
                            "</div>",
                        area: ['350px', '380px'],
                        btn: ['确认', '取消'],
                        offset: ['30%', '35%'],
                        yes: function (index) {
                            var employeeNo = $('#employee-no').val();
                            var name = $('#employee-name').val();
                            var sex = $('#employee-sex').val();
                            var age = $('#employee-age').val();
                            var position = $('#employee-position').val();
                            var authority = $('#employee-authority').val();
                            var salary = $('#employee-salary').val();
                            var time = $('#employee-time').val();
                            $.post(home.urls.employee.add, {
                                employno: employeeNo,
                                employname: name,
                                employsex: sex,
                                employage: age,
                                employposition: position,
                                employauthority: authority,
                                employpaymentpermonth: salary,
                                employworktime: time
                            }, function (result) {
                                console.log(result);
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        employeeInfo.init();
                                        clearTimeout(time);
                                    }, 500)
                                }
                                layer.close(index)
                            })
                        },
                        btn2: function (index) {
                            layer.close(index)
                        }
                    });
                });
            })
        },
        bindEditEventListener: function (editbtns) {
            editbtns.off('click');
            editbtns.on('click', function () {
                console.log("EDIT");
                var code = this.id.substr(5);
                console.log(code);
                $.get(home.urls.employee.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        layer.open({
                            type: 1,
                            title: '编辑',
                            content:
                                "<div id='addModal' class='popup'>" +
                                "<p>工号：<input type='number' id='employee-no' value='" + res.employno + "'/></p>" +
                                "<p>姓名：<input type='text' id='employee-name' value='" + res.employname + "'/></p>" +
                                "<p>性别：<select id='employee-sex'>" +
                                home.funcs.generateOption(2, home.vars.gender, res.employsex) +
                                "</select></p>" +
                                "<p>年龄：<input type='number' id='employee-age' value='" + res.employage + "'/></p>" +
                                "<p>职位：<select id='employee-position'>" +
                                home.funcs.generateOption(4, home.vars.position, res.employposition) +
                                "</select></p>" +
                                "<p>权限：<select id='employee-authority'>" +
                                home.funcs.generateOption(3,home.vars.authority, res.employauthority)+
                                "</select></p>" +
                                "<p>工资：<input type='number' id='employee-salary' value='" + res.employpaymentpermonth + "'/></p>" +
                                "<p>时段：<select id='employee-time'>" +
                                home.funcs.generateOption(3, home.vars.workTime, res.employworktime) +
                                "</select></p>" +
                                "</div>",
                            area: ['350px', '380px'],
                            btn: ['确认', '取消'],
                            offset: ['30%', '35%'],
                            yes: function (index) {
                                var employeeNo = $('#employee-no').val();
                                var name = $('#employee-name').val();
                                var sex = $('#employee-sex').val();
                                var age = $('#employee-age').val();
                                var position = $('#employee-position').val();
                                var authority = $('#employee-authority').val();
                                var salary = $('#employee-salary').val();
                                var time = $('#employee-time').val();
                                $.post(home.urls.employee.update + employeeNo, {
                                    // employno: employeeNo,
                                    employname: name,
                                    employsex: sex,
                                    employage: age,
                                    employposition: position,
                                    employauthority: authority,
                                    employpaymentpermonth: salary,
                                    employworktime: time
                                }, function (result) {
                                    console.log(result);
                                    layer.msg(result.msg, {
                                        offset: ['50%', '50%'],
                                        time: 700
                                    });
                                    if (result.code === 0) {
                                        var time = setTimeout(function () {
                                            employeeInfo.init();
                                            clearTimeout(time);
                                        }, 500)
                                    }
                                    layer.close(index)
                                })
                            },
                            btn2: function (index) {
                                layer.close(index)
                            }
                        });
                    })
                });
            })
        },
        bindDeleteEventListener: function (deleteBtns) {
            deleteBtns.off('click');
            deleteBtns.on('click', function () {
                console.log("DELETE");
                var code = this.id.substr(7);
                console.log(code);
                layui.use('layer', function () {
                    layer.open({
                        type: 1,
                        title: '删除',
                        content: "<h5 style='text-align: center;padding-top: 8px'>确认要删除该记录？</h5>",
                        area: ['180px', '130px'],
                        btn: ['确认', '取消'],
                        offset: ['35%', '40%'],
                        yes: function (index) {
                            console.log(code);
                            $.post(home.urls.employee.delete + code, {}, function (result) {
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        employeeInfo.init();
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