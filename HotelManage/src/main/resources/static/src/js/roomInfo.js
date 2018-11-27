var roomInfo = {
    pageSize: 0,
    init: function () {
        roomInfo.funcs.renderTable();
    },
    funcs: {
        renderTable: function () {
            $.get("/room/roomlist", {}, function (result) {

                var data = result.data;        //获取数据
                var $tbody = $("#room-info-table").children('tbody');
                roomInfo.funcs.renderHandler($tbody, data);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            roomInfo.funcs.bindRefreshEventListener(refreshBtn);
            var addBtn = $("#add");
            roomInfo.funcs.bindAddEventListener(addBtn);
            var searchBtn = $('#search');
            roomInfo.funcs.bindSearchEventListener(searchBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.roomno) + "</td>" +
                    "<td>" + (e.type) + "</td>" +
                    "<td>" + (e.price) + "</td>" +
                    "<td>" + (e.ifwindow) + "</td>" +
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
                            "<p>客房编号：<input type='number' id='room-no' required='required'/></p>" +
                            "<p>客房类型：<select id='room-type'>" +
                            "<option value='1'>1</option>" +
                            "<option value='2'>2</option>" +
                            "<option value='3'>3</option>" +
                            "<option value='4'>4</option>" +
                            "</select></p>" +
                            "<p>客房单价：<input type='number' id='room-price' required='required'/></p>" +
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
                            $.post("/room/add", {
                                roomno: roomno,
                                type: type,
                                price: price,
                                ifwindow: ifwindow,
                                comment: comment
                            }, function (result) {
                                layer.msg(result.message, {
                                    offset: ['40%', '55%'],
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
        /////////////////施工中//////////////////
        bindSearchEventListener: function (searchBtn) {
            searchBtn.off('click');
            searchBtn.on('click', function () {
                var equipment_name = $('#equipment_name_input').val()
                $.post(home.urls.equipment.getAllByLikeNameByPage(), {
                    name: equipment_name
                }, function (result) {
                    var page = result.data
                    var equipments = result.data.content //获取数据
                    $tbody = $("#equipment_table").children('tbody')
                    equipment_manage.funcs.renderHandler($tbody, equipments)
                    layui.laypage.render({
                        elem: 'equipment_page',
                        count: 10 * page.totalPages, //数据总数
                        jump: function (obj, first) {
                            $.post(home.urls.equipment.getAllByLikeNameByPage(), {
                                name: equipment_name,
                                page: obj.curr - 1,
                                size: obj.limit
                            }, function (result) {
                                var equipments = result.data.content //获取数据
                                $tbody = $("#equipment_table").children('tbody')
                                equipment_manage.funcs.renderHandler($tbody, equipments)
                                equipment_manage.pageSize = result.data.content.length
                            })
                        }
                    })
                })
            })
        },
        bindEditEventListener: function (editbtns) {
        },
        /////////////////施工中//////////////////
        bindDeleteEventListener: function (deleteBtns) {
            deleteBtns.off('click');
            deleteBtns.on('click', function () {
                console.log("DELETE");
                layui.use('layer', function () {
                    var _this = $(this);
                    layer.open({
                        type: 1,
                        title: '删除',
                        content: "<h5 style='text-align: center;padding-top: 8px'>确认要删除该记录？</h5>",
                        area: ['180px', '130px'],
                        btn: ['确认', '取消'],
                        offset: ['40%', '55%'],
                        yes: function (index) {
                            console.log(_this);
                            var code = _this.attr('id').substr(6);
                            $.post("/room/delete/" + code, {}, function (result) {
                                layer.msg(result.message, {
                                    offset: ['40%', '55%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        roomInfo.init()
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