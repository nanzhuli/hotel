var garage = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            garage.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.garage.getAll, {}, function (result) {

                var data = result.data;        //获取数据
                var $tbody = $("#garage-table").children('tbody');
                garage.funcs.renderHandler($tbody, data);
                var searchBtn = $('#search');
                garage.funcs.bindSearchEventListener(searchBtn, $tbody);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            garage.funcs.bindRefreshEventListener(refreshBtn);
            var addBtn = $("#add");
            garage.funcs.bindAddEventListener(addBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            var count = 0;
            data.forEach(function (e) {
                if (count % 5 === 0) {
                    $tbody.append("<tr>");
                }
                // 0外来，1住店
                $tbody.append(
                    "<td>" +
                    "<label id='" + e.garageno + "' class='detail'><button class='" + garage.funcs.getCarType(e.type, e.brand) + "'>" + e.garageno + "</button></label>" +
                    "</td>");
                count++;
                if (count % 5 === 0) {
                    $tbody.append("</tr>");
                }
            });
            var detailBtn = $('.detail');
            garage.funcs.bindDetailEventListener(detailBtn);
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
                        garage.init();
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
                            "<p>车位：<input type='number' id='garageNo'/></p>" +
                            "<p>类型：<select id='type'>" +
                            home.funcs.generateSelect(2, home.vars.garageType) +
                            "</select></p>" +
                            "<p>车牌：<input type='text' id='brand' maxlength='7'/></p>" +
                            "</div>",
                        area: ['350px', '380px'],
                        btn: ['确认', '取消'],
                        offset: ['30%', '35%'],
                        yes: function (index) {
                            var garageNo = $('#garageNo').val();
                            var type = $('#type').val();
                            var brand = $('#brand').val();
                            console.log(garageNo);
                            console.log(type);
                            console.log(brand);
                            $.post(home.urls.garage.driveIn, {
                                garageno: garageNo,
                                type: type,
                                brand: brand
                            }, function (result) {
                                console.log(result);
                                layer.msg(result.msg, {
                                    offset: ['50%', '50%'],
                                    time: 700
                                });
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        garage.init();
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
                var search = $('#search-bar').val();
                $.post(home.urls.garage.getByBrand + search, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        layer.open({
                            type: 1,
                            title: '搜索结果',
                            content:
                                "<div id='pop-div-room' class='display-table'>" +
                                "<table id='detail-table' class='layui-table' border='1px'>" +
                                "<tr><th colspan='3'>车位详情</th></tr>" +
                                "<tr><td>车位号</td><td>" + (res.garageno) + "</td></tr>" +
                                "<tr><td>类型</td><td>" + garage.funcs.getCarTypeForChart(res.type, res.brand) + "</td></tr>" +
                                "<tr><td>入库\n时间</td><td>" + home.funcs.spaceFunc(home.funcs.timeStr(res.starttime)) + "</td></tr>" +
                                "<tr><td>车牌</td><td>" + home.funcs.spaceFunc(res.brand) + "</td></tr>" +
                                "</table>" +
                                "</div>",
                            area: ['350px', '380px'],
                            btn: ['离库', "关闭"],
                            btnAlign: "c",
                            offset: ['30%', '35%'],
                            yes: function (index) {
                                console.log(res.garageno);
                                $.post(home.urls.garage.driveOut, {
                                    garageno: res.garageno
                                }, function (result) {
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
                                layer.close(index);
                            }
                        });
                    })
                })
            })
        },
        bindDetailEventListener: function (detailBtns) {
            detailBtns.off('click');
            detailBtns.on('click', function () {
                console.log("DETAIL");
                var code = this.id;
                console.log(code);
                $.get(home.urls.garage.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        layer.open({
                            type: 1,
                            title: '车位详情',
                            content:
                                "<div id='pop-div-room' class='display-table'>" +
                                "<table id='detail-table' class='layui-table' border='1px'>" +
                                "<tr><th colspan='3'>车位详情</th></tr>" +
                                "<tr><td>车位号</td><td>" + (res.garageno) + "</td></tr>" +
                                "<tr><td>类型</td><td>" + garage.funcs.getCarTypeForChart(res.type, res.brand) + "</td></tr>" +
                                "<tr><td>入库\n时间</td><td>" + home.funcs.spaceFunc(home.funcs.timeStr(res.starttime)) + "</td></tr>" +
                                "<tr><td>车牌</td><td>" + home.funcs.spaceFunc(res.brand) + "</td></tr>" +
                                "</table>" +
                                "</div>",
                            area: ['350px', '380px'],
                            btn: ["离库", '关闭'],
                            btnAlign: "c",
                            offset: ['30%', '35%'],
                            yes: function (index) {
                                console.log(res.garageno);
                                $.post(home.urls.garage.driveOut, {
                                    garageno: res.garageno
                                }, function (result) {
                                    layer.msg(result.msg, {
                                        offset: ['50%', '50%'],
                                        time: 700
                                    });
                                    if (result.code === 0) {
                                        var time = setTimeout(function () {
                                            garage.init();
                                            clearTimeout(time)
                                        }, 500)
                                    }
                                    layer.close(index)
                                })
                            },
                            btn2: function (index) {
                                layer.close(index);
                            }
                        });
                    })
                });
            })
        },
        getCarType: function (type, brand) {
            if (home.funcs.spaceFuncEmpty(brand) === "") {
                return "layui-btn";
            }
            // 0外来，1住店
            else {
                return (parseInt(type) === 0) ? "layui-btn layui-btn-warm" : "layui-btn layui-btn-danger";
            }
        },
        getCarTypeForChart: function (type, brand) {
            if (home.funcs.spaceFuncEmpty(brand) === "") {
                return "无";
            }
            // 0外来，1住店
            else {
                return home.vars.garageType[parseInt(type)];
            }
        }
    }
};