var roomInfo = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            roomInfo.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.roomInfo.getAll, {}, function (result) {

                var data = result.data;        //获取数据
                var $tbody = $("#room-info-table").children('tbody');
                roomInfo.funcs.renderHandler($tbody, data);
                var searchBtn = $('#search');
                roomInfo.funcs.bindSearchEventListener(searchBtn, $tbody);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            roomInfo.funcs.bindRefreshEventListener(refreshBtn);
            var addBtn = $("#add");
            roomInfo.funcs.bindAddEventListener(addBtn);
            var selectBtn = $("#select-bar");
            roomInfo.funcs.bindSelectEventListener(selectBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.roomno) + "</td>" +
                    "<td>" + home.type[e.type - 1] + "</td>" +
                    "<td>" + (e.price) + "</td>" +
                    "<td>" + (home.window[e.ifwindow]) + "</td>" +
                    "<td>" + (home.funcs.spaceFunc(e.comment)) + "</td>" +
                    "<td><a href='#' class='edits' id='edit-" + e.roomno + "'><i class=\"layui-icon layui-icon-edit\"></i></a></td>" +
                    "<td><a href='#' class='deletes' id='delete-" + e.roomno + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                    "</tr>")
            });
            // 数据渲染完毕
            var editBtns = $('.edits');
            var deleteBtns = $('.deletes');
            roomInfo.funcs.bindEditEventListener(editBtns);
            roomInfo.funcs.bindDeleteEventListener(deleteBtns);
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
                        roomInfo.init();
                        layer.close(index);
                        clearTimeout(time);
                    }, 200)
                });
            })
        },
        // 要做个输入合法性检测
        // 对齐的话，改成table会不会好一点？
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
                            "<p>客房编号：<input type='number' id='room-no'/></p>" +
                            "<p>客房类型：<select id='room-type'>" +
                            "<option value='1'>Single Room</option>" +
                            "<option value='2'>Double Room</option>" +
                            "<option value='3'>Business Room</option>" +
                            "<option value='4'>Family Room</option>" +
                            "</select></p>" +
                            "<p>客房单价：<input type='number' id='room-price'/></p>" +
                            "<p>是否有窗：<select id='ifwindow'>" +
                            "<option value='0'>无</option>" +
                            "<option value='1'>有</option>" +
                            "</select></p>" +
                            "<p>客房备注：<input type='text' id='comment'/></p>" +
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
                            $.post(home.urls.roomInfo.add, {
                                roomno: roomno,
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
                                        roomInfo.init();
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
                $.post(home.urls.roomInfo.getAll, {}, function (result) {
                    $tbody.empty();
                    var data = result.data;
                    for (var i = 0; i < data.length; i++) {
                        var flag = 0;
                        switch (parseInt(select)) {
                            case 0:
                                flag = (JSON.stringify(data[i].roomno).indexOf(search) !== -1);
                                break;
                            case 1:
                                flag = (parseInt(data[i].type) === parseInt(search));
                                break;
                            case 2:
                                flag = (JSON.stringify(data[i].price).indexOf(search) !== -1);
                                break;
                            case 3:
                                flag = (parseInt(data[i].ifwindow) === parseInt(search));
                                break;
                            case 4:
                                flag = (JSON.stringify(data[i].comment).indexOf(search) !== -1);
                                break;
                        }
                        if (flag) {
                            $tbody.append(
                                "<tr>" +
                                "<td>" + (data[i].roomno) + "</td>" +
                                "<td>" + home.type[data[i].type - 1] + "</td>" +
                                "<td>" + (data[i].price) + "</td>" +
                                "<td>" + (home.window[data[i].ifwindow]) + "</td>" +
                                "<td>" + (home.funcs.spaceFunc(data[i].comment)) + "</td>" +
                                "<td><a href='#' class='edits' id='edit-" + data[i].roomno + "'><i class=\"layui-icon layui-icon-edit\"></i></a></td>" +
                                "<td><a href='#' class='deletes' id='delete-" + data[i].roomno + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                                "</tr>")
                        }
                    }
                    // 数据渲染完毕
                    var editBtns = $('.edits');
                    var deleteBtns = $('.deletes');
                    roomInfo.funcs.bindEditEventListener(editBtns);
                    roomInfo.funcs.bindDeleteEventListener(deleteBtns);
                })
            })
        },
        bindEditEventListener: function (editbtns) {
            editbtns.off('click');
            editbtns.on('click', function () {
                console.log("EDIT");
                var code = this.id.substr(5);
                console.log(code);
                $.get(home.urls.roomInfo.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        layer.open({
                            type: 1,
                            title: '编辑',
                            content:
                                "<div id='addModal' class='popup'>" +
                                "<p>客房编号：<input type='number' id='room-no' value='" + res.roomno + "' disabled='disabled'/></p>" +
                                "<p>客房类型：<select id='room-type'>" +
                                roomInfo.funcs.selectType(res.type) +
                                "</select></p>" +
                                "<p>客房单价：<input type='number' id='room-price' value='" + res.price + "'/></p>" +
                                "<p>是否有窗：<select id='ifwindow'>" +
                                roomInfo.funcs.selectWindow(res.ifwindow) +
                                "</select></p>" +
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
                                $.post(home.urls.roomInfo.update + roomno, {
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
                                            roomInfo.init();
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
                            $.post(home.urls.roomInfo.delete + code, {}, function (result) {
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        roomInfo.init();
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
        bindSelectEventListener: function (selectBtn) {
            selectBtn.off('click');
            selectBtn.on('click', function () {
                var select = parseInt($('#select-bar').val());
                var element = $('#change')[0];
                console.log(element);
                if (select === 0 || select === 2 || select === 4) {
                    element.innerHTML = "<input type='text' id='search-bar' class='search-bar' placeholder='输入关键字'>";
                }
                else if (select === 1) {
                    element.innerHTML =
                        "<select id='search-bar' class='search-bar'>" +
                        "<option value='1'>Singe Room</option>" +
                        "<option value='2'>Double Room</option>" +
                        "<option value='3'>Business Room</option>" +
                        "<option value='4'>Family Room</option>" +
                        "</select>";
                }
                else {
                    element.innerHTML =
                        "<select id='search-bar' class='search-bar'>" +
                        "<option value='0'>无</option>" +
                        "<option value='1'>有</option>" +
                        "</select>";
                }
            });
        },
        selectType: function (data) {
            var str = "";
            for (var i = 1; i <= 4; i++) {
                if (i === data) {
                    str += "<option value='" + i + "' selected='selected'>" + home.type[i - 1] + "</option>";
                }
                else {
                    str += "<option value='" + i + "'>" + home.type[i - 1] + "</option>";
                }
            }
            return str;
        },
        selectWindow: function (data) {
            var str = "";
            if (data === 0) {
                str += "<option value='0' selected='selected'>无</option>" +
                    "<option value='1'>有</option>";
            }
            else {
                str += "<option value='0'>无</option>" +
                    "<option value='1' selected='selected'>有</option>";
            }
            return str;
        }
    }
};