var order = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            order.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.order.getAll, {}, function (result) {
                var data = result.data;        //获取数据
                var $tbody = $("#order-table").children('tbody');
                order.funcs.renderHandler($tbody, data);
                var searchBtn = $('#search');
                order.funcs.bindSearchEventListener(searchBtn, $tbody);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            order.funcs.bindRefreshEventListener(refreshBtn);
            var selectBtn = $("#select-bar");
            order.funcs.bindSelectEventListener(selectBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.orderno) + "</td>" +
                    "<td>" + (e.name) + "</td>" +
                    "<td>" + (e.phone) + "</td>" +
                    "<td>" + home.funcs.timeStrDate(e.starttime) + "</td>" +
                    "<td>" + home.funcs.timeStrDate(e.endtime) + "</td>" +
                    "<td>" + (e.price) + "</td>" +
                    "<td>" + (e.isenter) + "</td>" +
                    "<td><a href='#' class='mores' id='more-" + e.orderno + "'><i class=\"layui-icon layui-icon-list\"></i></a></td>" +
                    "<td><a href='#' class='edits' id='edit-" + e.orderno + "'><i class=\"layui-icon layui-icon-edit\"></i></a></td>" +
                    "<td><a href='#' class='deletes' id='delete-" + e.orderno + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                    "<td><a href='#' class='' id='submit-" + e.orderno + "'><i class=\"layui-icon layui-icon-ok-circle\"></i></a></td>" +
                    "</tr>")
            });
            // 数据渲染完毕
            var moreBtns = $('.mores');
            var editBtns = $('.edits');
            var deleteBtns = $('.deletes');
            var submitBtns = $('.submits');
            order.funcs.bindMoreEventListener(moreBtns);
            order.funcs.bindEditEventListener(editBtns);
            order.funcs.bindDeleteEventListener(deleteBtns);
            order.funcs.bindSubmitEventListener(submitBtns);
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
                        order.init();
                        layer.close(index);
                        clearTimeout(time);
                    }, 200)
                });
            })
        },
        bindSearchEventListener: function (searchBtn, $tbody) {
            searchBtn.off('click');
            searchBtn.on('click', function () {
                var select = $('#select-bar').val();
                var search = $('#search-bar').val();
                $.post(home.urls.order.getAll, {}, function (result) {
                    $tbody.empty();
                    var data = result.data;
                    for (var i = 0; i < data.length; i++) {
                        var flag = 0;
                        switch (parseInt(select)) {
                            case 0:
                                flag = (JSON.stringify(data[i].orderno).indexOf(search) !== -1);
                                break;
                            case 1:
                                flag = (JSON.stringify(data[i].name).indexOf(search) !== -1);
                                break;
                            case 2:
                                flag = (JSON.stringify(data[i].phone).indexOf(search) !== -1);
                                break;
                            case 3:
                                flag = (JSON.stringify(data[i].starttime).indexOf(search) !== -1);
                                break;
                            case 4:
                                flag = (JSON.stringify(data[i].endtime).indexOf(search) !== -1);
                                break;
                            case 5:
                                flag = (data[i].isenter === home.vars.enter[parseInt(search)]);
                                break;
                        }
                        if (flag) {
                            $tbody.append(
                                "<tr>" +
                                "<td>" + (data[i].orderno) + "</td>" +
                                "<td>" + (data[i].name) + "</td>" +
                                "<td>" + (data[i].phone) + "</td>" +
                                "<td>" + home.funcs.timeStrDate(data[i].starttime) + "</td>" +
                                "<td>" + home.funcs.timeStrDate(data[i].endtime) + "</td>" +
                                "<td>" + (data[i].price) + "</td>" +
                                "<td>" + (data[i].isenter) + "</td>" +
                                "<td><a href='#' class='mores' id='more-" + data[i].orderno + "'><i class=\"layui-icon layui-icon-list\"></i></a></td>" +
                                "<td><a href='#' class='edits' id='edit-" + data[i].orderno + "'><i class=\"layui-icon layui-icon-edit\"></i></a></td>" +
                                "<td><a href='#' class='deletes' id='delete-" + data[i].orderno + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                                "<td><a href='#' class='submits' id='submit-" + data[i].orderno + "'><i class=\"layui-icon layui-icon-ok-circle\"></i></a></td>" +
                                "</tr>")
                        }
                    }
                    // 数据渲染完毕
                    var editBtns = $('.edits');
                    var deleteBtns = $('.deletes');
                    order.funcs.bindEditEventListener(editBtns);
                    order.funcs.bindDeleteEventListener(deleteBtns);
                })
            })
        },
        bindMoreEventListener: function (moreBtn) {
        },
        bindEditEventListener: function (editbtns) {
            editbtns.off('click');
            editbtns.on('click', function () {
                console.log("EDIT");
                var code = this.id.substr(5);
                console.log(code);
                $.get(home.urls.order.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        layer.open({
                            type: 1,
                            title: '编辑',
                            content:
                                "<div id='addModal' class='popup'>" +
                                "<p>订单编号：<input type='number' id='room-no' value='" + res. + "' disabled='disabled'/></p>" +
                                "<p>客房单价：<input type='number' id='room-price' value='" + res.price + "'/></p>" +
                                "<p>客房备注：<input type='text' id='comment' value='" + home.funcs.spaceFuncEmpty(res.comment) + "'/></p>" +
                                "</div>",
                            area: ['350px', '380px'],
                            btn: ['确认', '取消'],
                            offset: ['30%', '35%'],
                            yes: function (index) {
                                var roomno = $('#room-no').val();
                                var type = $('#room-type').val();
                                var price = $('#room-price').val();
                                var ifwindow = $('#ifwindow').val();
                                var comment = $('#comment').val();
                                console.log(roomno);
                                console.log(type);
                                console.log(price);
                                console.log(ifwindow);
                                console.log(comment);
                                $.post(home.urls.order.update + roomno, {
                                    // roomno: roomno,
                                    type: type,
                                    price: price,
                                    ifwindow: ifwindow,
                                    comment: comment
                                }, function (result) {
                                    console.log(result);
                                    layer.msg(result.msg, {
                                        offset: ['50%', '50%'],
                                        time: 700
                                    });
                                    if (result.code === 0) {
                                        var time = setTimeout(function () {
                                            order.init();
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
                            $.post(home.urls.order.delete + code, {}, function (result) {
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        order.init();
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
        },
        bindSubmitEventListener: function (submitBtns) {
        },
        bindSelectEventListener: function (selectBtn) {
            selectBtn.off('click');
            selectBtn.on('click', function () {
                var select = parseInt($('#select-bar').val());
                var element = $('#change')[0];
                console.log(element);
                if (select === 5) {
                    element.innerHTML =
                        "<select id='search-bar' class='search-bar'>" +
                        "<option value='0'>" + home.vars.enter[0] + "</option>" +
                        "<option value='1'>" + home.vars.enter[1] + "</option>" +
                        "</select>";
                }
                else {
                    element.innerHTML = "<input type='text' id='search-bar' class='search-bar' placeholder='输入关键字'>";
                }
            });
        }
    }
};