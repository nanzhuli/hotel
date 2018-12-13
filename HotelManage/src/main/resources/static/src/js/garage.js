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
                            "<p>客房编号：<input type='number' id='room-no'/></p>" +
                            "<p>客房备注：<input type='text' id='comment'/></p>" +
                            "</div>",
                        area: ['350px', '380px'],
                        btn: ['确认', '取消'],
                        offset: ['30%', '35%'],
                        yes: function (index) {
                            var roomno = $('#room-no').val();
                            var comment = $('#comment').val();
                            var type = roomno.substring(0, 1);
                            console.log(roomno);
                            console.log(comment);
                            console.log(type);
                            $.post(home.urls.garage.add, {
                                roomno: roomno,
                                type: type,
                                comment: comment
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
                var select = $('#select-bar').val();
                var search = $('#search-bar').val();
                $.post(home.urls.garage.getAll, {}, function (result) {
                    $tbody.empty();
                    var data = result.data;
                    for (var i = 0; i < data.length; i++) {
                        var flag = 0;
                        switch (parseInt(select)) {
                            case 0:
                                flag = (JSON.stringify(data[i].garageno).indexOf(search) !== -1);
                                break;
                            case 1:
                                flag = (JSON.stringify(data[i].type).indexOf(search) !== -1);
                                break;
                            case 2:
                                flag = (JSON.stringify(data[i].roomno).indexOf(search) !== -1);
                                break;
                            case 3:
                                flag = (JSON.stringify(data[i].employno).indexOf(search) !== -1);
                                break;
                            case 4:
                                flag = (JSON.stringify(data[i].comment).indexOf(search) !== -1);
                                break;
                        }
                        if (flag) {
                            $tbody.append(
                                "<tr>" +
                                "<td>" + (data[i].garageno) + "</td>" +
                                "<td>" + home.vars.typeEvent[data[i].type] + "</td>" +
                                "<td>" + (data[i].roomno) + "</td>" +
                                "<td>" + (data[i].employno) + "</td>" +
                                "<td>" + home.funcs.spaceFunc(data[i].comment) + "</td>" +
                                "<td>" + home.funcs.timeStr(data[i].starttime) + "</td>" +
                                "<td>" + home.funcs.timeStr(data[i].endtime) + "</td>" +
                                "<td><a href='#' class='edits' id='edit-" + data[i].garageno + "'><i class=\"layui-icon layui-icon-edit\"></i></a></td>" +
                                "<td><a href='#' class='deletes' id='delete-" + data[i].garageno + "'><i class=\"layui-icon layui-icon-delete\"></i></a></td>" +
                                "</tr>")
                        }
                    }
                    // 数据渲染完毕
                    var editBtns = $('.edits');
                    var deleteBtns = $('.deletes');
                    garage.funcs.bindEditEventListener(editBtns);
                    garage.funcs.bindDeleteEventListener(deleteBtns);
                })
            })
        },
        bindEditEventListener: function (editbtns) {
            editbtns.off('click');
            editbtns.on('click', function () {
                console.log("EDIT");
                var code = this.id.substr(5);
                console.log(code);
                $.get(home.urls.garage.getOne + code, {}, function (result) {
                    console.log(result);
                    var res = result.data;
                    layui.use('layer', function () {
                        layer.open({
                            type: 1,
                            title: '编辑',
                            content:
                                "<div id='addModal' class='popup'>" +
                                "<p>事务编号：<input type='number' id='garage-no' value='" + res.garageno + "' disabled='disabled'/></p>" +
                                "<p>客房编号：<input type='number' id='room-no' value='" + res.roomno + "'/></p>" +
                                "<p>客房备注：<input type='text' id='comment' value='" + home.funcs.spaceFuncEmpty(res.comment) + "'/></p>" +
                                "</div>",
                            area: ['350px', '380px'],
                            btn: ['确认', '取消'],
                            offset: ['30%', '35%'],
                            yes: function (index) {
                                var garageno = $('#garage-no').val();
                                var roomno = $('#room-no').val();
                                var type = roomno.substring(0, 1);
                                var comment = $('#comment').val();
                                console.log(garageno);
                                console.log(roomno);
                                console.log(type);
                                console.log(comment);
                                $.post(home.urls.garage.update + garageno, {
                                    roomno: roomno,
                                    type: type,
                                    comment: comment
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
                            $.post(home.urls.garage.delete + code, {}, function (result) {
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
                            layer.close(index)
                        }
                    })
                })
            })
        },
        bindDetailEventListener: function(detailBtns){
            detailBtns.off('click');
            detailBtns.on('click', function () {
                console.log("DETAIL");
                var code = this.id;
                console.log(code);
                // $.get(home.urls.garage.getOne + code, {}, function (result) {
                //     console.log(result);
                //     var res = result.data;
                //     layui.use('layer', function () {
                //         layer.open({
                //             type: 1,
                //             title: '编辑',
                //             content:
                //                 "<div id='addModal' class='popup'>" +
                //                 "<p>事务编号：<input type='number' id='garage-no' value='" + res.garageno + "' disabled='disabled'/></p>" +
                //                 "<p>客房编号：<input type='number' id='room-no' value='" + res.roomno + "'/></p>" +
                //                 "<p>客房备注：<input type='text' id='comment' value='" + home.funcs.spaceFuncEmpty(res.comment) + "'/></p>" +
                //                 "</div>",
                //             area: ['350px', '380px'],
                //             btn: ['确认', '取消'],
                //             offset: ['30%', '35%'],
                //             yes: function (index) {
                //                 var garageno = $('#garage-no').val();
                //                 var roomno = $('#room-no').val();
                //                 var type = roomno.substring(0, 1);
                //                 var comment = $('#comment').val();
                //                 console.log(garageno);
                //                 console.log(roomno);
                //                 console.log(type);
                //                 console.log(comment);
                //                 $.post(home.urls.garage.update + garageno, {
                //                     roomno: roomno,
                //                     type: type,
                //                     comment: comment
                //                 }, function (result) {
                //                     console.log(result);
                //                     layer.msg(result.msg, {
                //                         offset: ['50%', '50%'],
                //                         time: 700
                //                     });
                //                     if (result.code === 0) {
                //                         var time = setTimeout(function () {
                //                             garage.init();
                //                             clearTimeout(time);
                //                         }, 500)
                //                     }
                //                     layer.close(index)
                //                 })
                //             },
                //             btn2: function (index) {
                //                 layer.close(index)
                //             }
                //         });
                //     })
                // });
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
        }
    }
};