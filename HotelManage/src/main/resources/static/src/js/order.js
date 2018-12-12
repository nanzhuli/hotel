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
                    "<td>" + home.vars.enter[e.isenter] + "</td>" +
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
                                flag = (JSON.stringify(data[i].isenter).indexOf(search) !== -1);
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
                                "<td>" + home.vars.enter[data[i].isenter] + "</td>" +
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
            moreBtn.off('click');
            moreBtn.on('click', function () {
                console.log("MORE");
                var code = this.id.substr(5);
                console.log(code);
                // 获取订单信息
                $.get(home.urls.order.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    // var str = "";
                    layui.use('layer', function () {
                        // 使用订单号查找订单所包含的所有房间
                        $.get(home.urls.room.getAll + res.orderno, {}, function (allRoom) {
                            console.log(allRoom);
                            var allRoomData = allRoom.data;
                            allRoomData.forEach(function (each) {
                                // 插一行房间信息和查看按钮
                                var str = "<tr><td>" + each.roomno + "</td><td>" + home.funcs.spaceFunc(each.brand) + "</td>" +
                                    "<td><a href='#' class='rooms' id='room-" + each.roomno + "'><i class=\"layui-icon layui-icon-list\"></i></a></td></tr>";
                                order.funcs.detailHandler(str);
                                // 绑定查看按钮事件
                                var roomBtns = $(".rooms");
                                order.funcs.bindRoomsEventListener(roomBtns);
                            });
                        });
                        // console.log(str);
                        layer.open({
                            type: 1,
                            title: '查看',
                            content:
                                "<div id='pop-div-room' class='display-table'>" +
                                "<table id='detail-table' class='layui-table' border='1px'>" +
                                "<tr><th colspan='3'>订单详情</th></tr>" +
                                "<tr><td>订单号</td><td colspan='2'>" + (res.orderno) + "</td></tr>" +
                                "<tr><td>房间数量</td><td colspan='2'>" + (res.roomcount) + "</td></tr>" +
                                "<tr><td>订单价格</td><td colspan='2'>" + (res.price) + "</td></tr>" +
                                "<tr><td>姓名</td><td colspan='2'>" + (res.name) + "</td></tr>" +
                                "<tr><td>身份证号</td><td colspan='2'>" + (res.id) + "</td></tr>" +
                                "<tr><td>联系方式</td><td colspan='2'>" + (res.phone) + "</td></tr>" +
                                "<tr><td>入住时间</td><td colspan='2'>" + home.funcs.timeStrDate(res.starttime) + "</td></tr>" +
                                "<tr><td>离开时间</td><td colspan='2'>" + home.funcs.timeStrDate(res.endtime) + "</td></tr>" +
                                "<tr><td>是否会员</td><td colspan='2'>" + home.vars.member[res.ismenber] + "</td></tr>" +
                                "<tr><td>是否入住</td><td colspan='2'>" + home.vars.enter[res.isenter] + "</td></tr>" +
                                "<tr><th colspan='3'>房间详情</th></tr>" +
                                "<tr><td>房间号</td><td>绑定车牌</td><td>查看房间信息</td></tr>" +
                                "</table>" +
                                "</div>",
                            area: ['600px', '600px'],
                            btn: ['确认'],
                            offset: 'auto',// ['5%', '35%'],
                            zIndex: 5,
                            btnAlign: 'c',
                            yes: function (index) {
                                layer.close(index)
                            }
                        });
                    });
                });
            });
        },
        bindEditEventListener: function (editbtns) {
            editbtns.off('click');
            editbtns.on('click', function () {
                console.log("EDIT");
                var code = this.id.substr(5);
                console.log(code);
                // 获取订单信息
                $.get(home.urls.order.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        // 使用订单号查找订单所包含的所有房间
                        $.get(home.urls.room.getAll + res.orderno, {}, function (allRoom) {
                            console.log(allRoom);
                            var allRoomData = allRoom.data;
                            allRoomData.forEach(function (each) {
                                // 插一行房间信息和编辑按钮
                                var str = "<tr>" +
                                    "<td id='roomno-" + each.orno + "'>" + order.funcs.getEmptyRoom(each.roomno, each.orno) + "</td>" +
                                    "<td><input type='text' style='width:66px' id='brand-" + each.orno + "' maxlength='7' value='" + home.funcs.spaceFunc(each.brand) + "'></td>" +
                                    // "<td><button class='layui-btn layui-btn-primary layui-btn-xs'><label class='room-update' id='room-update-" + each.roomno + "'>保存</label></td></button>" +
                                    "<td><a href='#' class='room-update' id='room-update-" + each.orno + "'><i class='layui-icon layui-icon-ok'></i></a></td>" +
                                    "<td><a href='#' class='edit-rooms' id='room-edit-" + each.roomno + "'><i class='layui-icon layui-icon-edit'></i></a></td>" +
                                    "</tr>";
                                order.funcs.detailHandler(str);
                                // 绑定查看按钮事件
                                var roomBtns = $(".edit-rooms");
                                order.funcs.bindEditRoomEventListener(roomBtns);
                                var roomUpdate = $(".room-update");
                                order.funcs.bindRoomUpdateEventListener(roomUpdate);
                            });
                        });
                        // console.log(str);
                        layer.open({
                            type: 1,
                            title: '编辑',
                            content:
                                "<div id='pop-div-room' class='display-table'>" +
                                "<table id='detail-table' class='layui-table' border='1px'>" +
                                "<tr><th colspan='4'>订单详情</th></tr>" +
                                "<tr><td>订单号</td><td colspan='3' id='orderno'>" + (res.orderno) + "</td></tr>" +
                                "<tr><td>房间数量</td><td colspan='3' id='roomcount'>" + (res.roomcount) + "</td></tr>" +
                                "<tr><td>订单价格</td><td colspan='3' id='price'>" + (res.price) + "</td></tr>" +
                                "<tr><td>姓名</td><td colspan='3'><input id='name' style='width: 145px' type='text' value='" + (res.name) + "'>" +
                                "&nbsp;&nbsp;<button class='layui-btn layui-btn-primary layui-btn-xs'><label class='order-update'>全部保存</label></button></td></tr>" +
                                "<tr><td>身份证号</td><td colspan='3'><input id='id' style='width: 145px' type='text' maxlength='18' value='" + (res.id) + "'>" +
                                "&nbsp;&nbsp;<button class='layui-btn layui-btn-primary layui-btn-xs'><label class='order-update'>全部保存</label></button></td></tr>" +
                                "<tr><td>联系方式</td><td colspan='3'><input id='phone' style='width: 145px' type='number' oninput='if(value.length>11)value=value.slice(0,11)' value='" + (res.phone) + "'>" +
                                "&nbsp;&nbsp;<button class='layui-btn layui-btn-primary layui-btn-xs'><label class='order-update'>全部保存</label></button></td></tr>" +
                                "<tr><td>入住时间</td><td colspan='3' id='starttime'>" + home.funcs.timeStrDate(res.starttime) + "</td></tr>" +
                                "<tr><td>离开时间</td><td colspan='3' id='endtime'>" + home.funcs.timeStrDate(res.endtime) + "</td></tr>" +
                                "<tr><td>是否会员</td><td colspan='3' id='ismenber'>" + home.vars.member[res.ismenber] + "</td></tr>" +
                                "<tr><td>是否入住</td><td colspan='3'><select id='isenter' style='width: 145px; height: 22px'>" + home.funcs.generateOption(2, home.vars.enter, res.isenter) + "</select>" +
                                "&nbsp;&nbsp;<button class='layui-btn layui-btn-primary layui-btn-xs'><label class='order-update'>保存</label></button></td></tr>" +
                                "<tr><th colspan='4'>房间详情</th></tr>" +
                                "<tr><td>房间号</td><td>绑定车牌</td><td>保存</td><td>编辑房客</td></tr>" +
                                "</table>" +
                                "</div>",
                            area: ['600px', '600px'],
                            btn: ['确认'],
                            offset: 'auto',// ['5%', '35%'],
                            zIndex: 5,
                            btnAlign: 'c',
                            success: function () {
                                var updateBtn = $(".order-update");
                                order.funcs.bindOrderUpdateEventListener(updateBtn);
                            },
                            yes: function (index) {
                                layer.close(index)
                            }
                        });
                    });
                });
            })
        },
        bindEditRoomEventListener: function (roomBtn) {
            roomBtn.off('click');
            roomBtn.on('click', function () {
                console.log("EDIT-ROOM");
                var code = this.id.substr(10);
                console.log(code);
                layui.use('layer', function () {
                    // 使用code (roomno) 检索 room-id 表中的信息
                    $.get(home.urls.roomid.getAll + code, {}, function (allRoomId) {
                        console.log(allRoomId);
                        var allRoomIdData = allRoomId.data;
                        allRoomIdData.forEach(function (each) {
                            // 插一行用户信息
                            var str = "<tr><td>" + each.roomno + "</td>" +
                                "<td><input id='name-" + each.rino + "' style='width: 80px' type='text' value='" + each.name + "'></td>" +
                                "<td><input id='id-" + each.rino + "' style='width: 145px' type='text' maxlength='18' value='" + each.id + "'></td>" +
                                "<td><a href='#' class='id-update' id='id-update-" + each.rino + "'><i class='layui-icon layui-icon-ok'></i></a></td></tr>";
                            order.funcs.detailRoomHandler(str);
                            var updateBtn = $(".id-update");
                            order.funcs.bindIdUpdateEventListener(updateBtn);
                        });
                    });
                    layer.open({
                        type: 1,
                        title: '编辑房间' + code,
                        content:
                            "<div id='pop-div-roomid' class='display-table'>" +
                            "<table id='room-detail-table' class='layui-table' border='1px' width='70%'>" +
                            "<tr><td>房间号</td><td>姓名</td><td>身份证号</td><td>保存</td></tr>" +
                            "</table>" +
                            "</div>",
                        area: ['600px', '400px'],
                        btn: ['确认'],
                        offset: 'auto', //['5%', '35%'],
                        zIndex: 10,
                        btnAlign: 'c',
                        yes: function (index) {
                            layer.close(index)
                        }
                    })
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
                        offset: 'auto',//: ['35%', '40%'],
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
        },
        bindRoomsEventListener: function (roomBtn) {
            roomBtn.off('click');
            roomBtn.on('click', function () {
                console.log("ROOM");
                var code = this.id.substr(5);
                console.log(code);
                layui.use('layer', function () {
                    // 使用code (roomno) 检索 room-id 表中的信息
                    $.get(home.urls.roomid.getAll + code, {}, function (allRoomId) {
                        console.log(allRoomId);
                        var allRoomIdData = allRoomId.data;
                        allRoomIdData.forEach(function (each) {
                            // 插一行用户信息
                            var str = "<tr><td>" + each.roomno + "</td><td>" + each.name + "</td><td>" + each.id + "</td></tr>";
                            order.funcs.detailRoomHandler(str);
                        });
                    });
                    layer.open({
                        type: 1,
                        title: '房间' + code,
                        content:
                            "<div id='pop-div-roomid' class='display-table'>" +
                            "<table id='room-detail-table' class='layui-table' border='1px' width='70%'>" +
                            "<tr><td>房间号</td><td>姓名</td><td>身份证号</td></tr>" +
                            "</table>" +
                            "</div>",
                        area: ['500px', '400px'],
                        btn: ['确认'],
                        offset: 'auto', //['5%', '35%'],
                        zIndex: 10,
                        btnAlign: 'c',
                        success: function (layero) {
                            layer.setTop(layero); //重点2
                        },
                        yes: function (index) {
                            layer.close(index)
                        }
                    })
                })
            })
        },
        detailHandler: function (addStr) {
            var $tbody = $("#detail-table").children('tbody');
            $tbody.append(addStr);
        },
        detailRoomHandler: function (addStr) {
            var $tbody = $("#room-detail-table").children('tbody');
            $tbody.append(addStr);
        },
        getEmptyRoom: function (originRoom, orno) {
            $.get(home.urls.roomid.getEmpty, {}, function (emptyRooms) {
                console.log(emptyRooms);
                var emptyRoomsData = emptyRooms.data;
                var str = "<select id='empty-room'>";
                // 增加选项、默认初始和可用的空房间
                str += "<option selected='selected' value='" + originRoom + "'>" + originRoom + "</option>";
                emptyRoomsData.forEach(function (emptyRoom) {
                    str += "<option value='" + emptyRoom + "'>" + emptyRoom + "</option>";
                });
                // 保存按钮，绑定房间号
                str += "</select>";
                var element = $("#roomno-" + orno)[0];
                element.innerHTML = str;
            })
        },
        bindOrderUpdateEventListener: function (updateBtn) {
            updateBtn.off('click');
            updateBtn.on('click', function () {
                console.log("OrderUpdate");
                var roomno = $("#orderno")[0].innerHTML;
                var roomcount = $("#roomcount")[0].innerHTML;
                var price = $("#price")[0].innerHTML;
                var name = $("#name").val();
                var id = $("#id").val();
                var phone = $("#phone").val();
                var starttime = $("#starttime")[0].innerHTML;
                var endtime = $("#endtime")[0].innerHTML;
                var ismenber = home.funcs.indexOf(home.vars.member, $("#ismenber")[0].innerHTML);
                var isenter = $("#isenter").val();
                // console.log(roomno);
                // console.log(roomcount);
                // console.log(price);
                // console.log(name);
                // console.log(id);
                // console.log(phone);
                // console.log(starttime);
                // console.log(endtime);
                // console.log(ismenber);
                // console.log(isenter);
                $.post(home.urls.order.update + roomno, {
                    name: name,
                    id: id,
                    phone: phone,
                    isenter: isenter
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
                    layer.closeAll();
                })
            });
        },
        bindRoomUpdateEventListener: function (updateBtn) {
            updateBtn.off('click');
            updateBtn.on('click', function () {
                console.log("RoomUpdate");
                var orno = this.id.substr(12);
                // console.log(orno);
                var roomno = $("#roomno-" + orno).children("select").val();
                var brand = $("#brand-" + orno).val();
                console.log(roomno);
                console.log(brand);
            });
        },
        bindIdUpdateEventListener: function (updateBtn) {
            updateBtn.off('click');
            updateBtn.on('click', function () {
                console.log("RoomUpdate");
                var rino = this.id.substr(10);
                console.log(rino);
                var name = $("#name-" + rino).val();
                var id = $("#id-" + rino).val();
                console.log(name);
                console.log(id);
            });
        }
    }
};