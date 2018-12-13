var member = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            member.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.member.getAll, {}, function (result) {

                var data = result.data;        //获取数据
                var $tbody = $("#member-table").children('tbody');
                member.funcs.renderHandler($tbody, data);
                var searchBtn = $('#search');
                member.funcs.bindSearchEventListener(searchBtn, $tbody);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            member.funcs.bindRefreshEventListener(refreshBtn);
            var addBtn = $("#add");
            member.funcs.bindAddEventListener(addBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.memberno) + "</td>" +
                    "<td>" + (e.name) + "</td>" +
                    "<td>" + (e.phone) + "</td>" +
                    "<td>" + (e.id) + "</td>" +
                    "<td>" + home.funcs.timeStr(e.entertime) + "</td>" +
                    "<td><a href='#' class='deletes' id='delete-" + e.phone + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                    "</tr>")
            });
            // 数据渲染完毕
            var deleteBtns = $('.deletes');
            member.funcs.bindDeleteEventListener(deleteBtns);
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
                        member.init();
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
                            "<p>客户姓名：<input type='text' id='name'/></p>" +
                            "<p>电话号码：<input type='number' id='phone' oninput='if(value.length>11)value=value.slice(0,11)'/></p>" +
                            "<p>身份证号：<input type='text' id='id' maxlength='18'/></p>" +
                            "</div>",
                        area: ['350px', '380px'],
                        btn: ['确认', '取消'],
                        offset: ['30%', '35%'],
                        yes: function (index) {
                            var name = $('#name').val();
                            var phone = $('#phone').val();
                            var id = $('#id').val();
                            console.log(name);
                            console.log(phone);
                            console.log(id);
                            $.post(home.urls.member.add, {
                                name: name,
                                phone: phone,
                                id: id
                            }, function (result) {
                                console.log(result);
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        member.init();
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
        bindSearchEventListener: function (searchBtn, $tbody) {
            searchBtn.off('click');
            searchBtn.on('click', function () {
                var select = $('#select-bar').val();
                var search = $('#search-bar').val();
                $.post(home.urls.member.getAll, {}, function (result) {
                    $tbody.empty();
                    var data = result.data;
                    for (var i = 0; i < data.length; i++) {
                        var flag = 0;
                        switch (parseInt(select)) {
                            case 0:
                                flag = (JSON.stringify(data[i].memberno).indexOf(search) !== -1);
                                break;
                            case 1:
                                flag = (JSON.stringify(data[i].name).indexOf(search) !== -1);
                                break;
                            case 2:
                                flag = (JSON.stringify(data[i].phone).indexOf(search) !== -1);
                                break;
                            case 3:
                                flag = (JSON.stringify(data[i].id).indexOf(search) !== -1);
                                break;
                            case 4:
                                flag = (JSON.stringify(data[i].entertime).indexOf(search) !== -1);
                                break;
                        }
                        if (flag) {
                            $tbody.append(
                                "<tr>" +
                                "<td>" + (data[i].memberno) + "</td>" +
                                "<td>" + (data[i].name) + "</td>" +
                                "<td>" + (data[i].phone) + "</td>" +
                                "<td>" + (data[i].id) + "</td>" +
                                "<td>" + home.funcs.timeStr(data[i].entertime) + "</td>" +
                                "<td><a href='#' class='deletes' id='delete-" + data[i].phone + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                                "</tr>")
                        }
                    }
                    // 数据渲染完毕
                    var deleteBtns = $('.deletes');
                    member.funcs.bindDeleteEventListener(deleteBtns);
                })
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
                            $.post(home.urls.member.delete, {
                                phone:code,
                            }, function (result) {
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        member.init();
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