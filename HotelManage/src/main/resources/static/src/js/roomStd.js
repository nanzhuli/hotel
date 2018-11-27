var roomStd = {
    // department_result: [],
    // productline_result: [],
    // user_result: [],
    // do_once: function() {
    //     $.get(home.urls.department.getAll(), function(result) {
    //         roomStd.department_result = result.data;
    //     });
    //
    //     $.get(home.urls.productline.getAll(), function(result) {
    //         roomStd.productline_result = result.data;
    //     });
    //
    //     $.get(home.urls.user.getAll(), function(result) {
    //         roomStd.user_result = result.data;
    //     });
    // },
    pageSize: 0,
    init: function () {
        // document.write("<script src=\"../js/roomStd.js\"></script>");
        roomStd.funcs.renderTable();
    },

    funcs: {
        renderTable: function () {
            // $.get(home.urls.roomStd.getStd(), {
            $.get("/standard", {}, function (result) {
                var data = result.data;        //获取数据
                var $tbody = $("#roomStd_table").children('tbody');
                roomStd.funcs.renderHandler($tbody, data);
                // roomStd.pageSize = result.data.content.length;
                // var page = result.data;
                // /** @namespace page.totalPages 这是返回数据的总页码数 */
                // layui.laypage.render({
                //     elem: 'stdPage',
                //     count: 10 * page.totalPages, //数据总数
                //
                //     jump: function(obj, first) {
                //         if(!first){
                //             $.post(home.urls.equipment.getAllByPage(), {
                //                 page: obj.curr - 1,
                //                 size: obj.limit
                //             }, function(result) {
                //                 var equipments = result.data.content //获取数据
                //                 $tbody = $("#equipment_table").children('tbody')
                //                 equipment_manage.funcs.renderHandler($tbody, equipments)
                //                 equipment_manage.pageSize = result.data.content.length
                //             })
                //         }
                //     }
                // })
                // $('#equipment_page').css('padding-left', '37%')
            });
            //$数据渲染完毕
            // var addBtn = $("#model-li-hide-add-60")
            // equipment_manage.funcs.bindAddEventListener(addBtn) //追加增加事件
            // var refreshBtn = $('#model-li-hide-refresh-60')
            // equipment_manage.funcs.bindRefreshEventLisener(refreshBtn) //追加刷新事件
            // var searchBtn = $('#model-li-hide-search-60')
            // equipment_manage.funcs.bindSearchEventListener(searchBtn)
        },
        bindAddEventListener: function (addBtn) {
            addBtn.off('click')
            addBtn.on('click', function () {

                //首先就是弹出一个弹出框
                layer.open({
                    type: 1,
                    title: '添加',
                    content: "<div id='addModal'>" +
                        "<div style='text-align: center;padding-top: 10px;'>" +
                        "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;设备编码:<input type='text' id='code'/></p>" +
                        "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;设备名称:<input type='text' id='name'/></p>" +
                        "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;所属部门:<select id='department_code' style='width:174px;'></select></p>" +
                        "<p style='padding: 5px 0px 5px 0px;'>所属产品线:<select id='productline_code' style='width:174px;'></select></p>" +
                        "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;巡检人:<select id='user_code' style='width:174px;'></select></p>" +
                        "</div>" +
                        "</div>",
                    area: ['350px', '260px'],
                    btn: ['确认', '取消'],
                    offset: ['30%', '35%'],
                    yes: function (index) {
                        var code = $('#code').val()
                        var name = $('#name').val()
                        var department_code = $('#department_code').val()
                        var productline_code = $('#productline_code').val()
                        var user_code = $('#user_code').val()
                        $.post(home.urls.equipment.add(), {
                            code: code,
                            name: name,
                            departmentCode: department_code,
                            productLineCode: productline_code,
                            inspectorCode: user_code
                        }, function (result) {
                            layer.msg(result.message, {
                                offset: ['40%', '55%'],
                                time: 700
                            })
                            if (result.code === 0) {
                                var time = setTimeout(function () {
                                    equipment_manage.init()
                                    clearTimeout(time)
                                }, 500)
                            }
                            layer.close(index)
                        })
                    },
                    btn2: function (index) {
                        layer.close(index)
                    }
                });

                var department_select = $("#department_code");
                department_select.empty();
                equipment_manage.department_result.forEach(function (department) {
                    var option = $("<option>").val(department.code).text(department.name);
                    department_select.append(option);
                });

                var productline_select = $("#productline_code");
                productline_select.empty();
                equipment_manage.productline_result.forEach(function (productline) {
                    var option = $("<option>").val(productline.code).text(productline.name);
                    productline_select.append(option);
                });

                var user_select = $("#user_code");
                user_select.empty();
                equipment_manage.user_result.forEach(function (user) {
                    var option = $("<option>").val(user.code).text(user.name);
                    user_select.append(option);
                });

            })

        },
        bindDeleteEventListener: function (deleteBtns) {
            deleteBtns.off('click')
            deleteBtns.on('click', function () {
                //首先弹出一个询问框
                var _this = $(this)
                layer.open({
                    type: 1,
                    title: '删除',
                    content: "<h5 style='text-align: center;padding-top: 8px'>确认要删除该记录?</h5>",
                    area: ['180px', '130px'],
                    btn: ['确认', '取消'],
                    offset: ['40%', '55%'],
                    yes: function (index) {
                        var equipmentCode = _this.attr('id').substr(3)
                        $.post(home.urls.equipment.deleteByCode(), {
                            code: equipmentCode
                        }, function (result) {
                            layer.msg(result.message, {
                                offset: ['40%', '55%'],
                                time: 700
                            })
                            if (result.code === 0) {
                                var time = setTimeout(function () {
                                    equipment_manage.init()
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
        },
        bindSearchEventListener: function (searchBtn) {

            searchBtn.off('click')
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
        bindRefreshEventLisener: function (refreshBtn) {
            refreshBtn.off('click')
            refreshBtn.on('click', function () {
                var index = layer.load(2, {
                    offset: ['40%', '58%']
                });
                var time = setTimeout(function () {
                    layer.msg('刷新成功', {
                        offset: ['40%', '55%'],
                        time: 700
                    })
                    equipment_manage.init()
                    layer.close(index)
                    clearTimeout(time)
                }, 200)
            })
        },
        bindSelectAll: function (selectAllBox) {
            selectAllBox.off('change')
            selectAllBox.on('change', function () {
                var status = selectAllBox.prop('checked')
                $('.checkbox').each(function () {
                    $(this).prop('checked', status)
                })
            })
        },
        bindDeleteBatchEventListener: function (deleteBatchBtn) {
            deleteBatchBtn.off('click')
            deleteBatchBtn.on('click', function () {
                if ($('.checkbox:checked').length === 0) {
                    layer.msg('亲,您还没有选中任何数据！', {
                        offset: ['40%', '55%'],
                        time: 700
                    })
                } else {
                    layer.open({
                        type: 1,
                        title: '批量删除',
                        content: "<h5 style='text-align: center;padding-top: 8px'>确认要删除选中记录吗?</h5>",
                        area: ['190px', '130px'],
                        btn: ['确认', '取消'],
                        offset: ['40%', '55%'],
                        yes: function (index) {
                            var equipmentCodes = []
                            $('.checkbox').each(function () {
                                if ($(this).prop('checked')) {
                                    equipmentCodes.push({
                                        code: $(this).val()
                                    })
                                }
                            })
                            $.ajax({
                                url: home.urls.equipment.deleteByBatchCode(),
                                contentType: 'application/json',
                                data: JSON.stringify(equipmentCodes),
                                dataType: 'json',
                                type: 'post',
                                success: function (result) {
                                    if (result.code === 0) {
                                        var time = setTimeout(function () {
                                            equipment_manage.init()
                                            clearTimeout(time)
                                        }, 500)
                                    }
                                    layer.msg(result.message, {
                                        offset: ['40%', '55%'],
                                        time: 700
                                    })
                                }
                            })
                            layer.close(index)
                        },
                        btn2: function (index) {
                            layer.close(index)
                        }
                    })
                }
            })
        },
        bindEditEventListener: function (editBtns) {
            editBtns.off('click')
            editBtns.on('click', function () {
                var _selfBtn = $(this)
                var equipmentCode = _selfBtn.attr('id').substr(5)
                $.post(home.urls.equipment.getByCode(), {
                    code: equipmentCode
                }, function (result) {
                    var equipment = result.data
                    layer.open({
                        type: 1,
                        title: '编辑',
                        content: "<div id='addModal'>" +
                            "<div style='text-align: center;padding-top: 10px;'>" +
                            "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;工序编码:<input type='text' id='code' value='" + (equipment.code) + "'/></p>" +
                            "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;工序名称:<input type='text' id='name' value='" + (equipment.name) + "'/></p>" +
                            "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;所属部门:<select id='department_code' style='width:174px;'><option value='+" + (equipment.department && equipment.department.code || '') + "'>" + (equipment.department && equipment.department.name || '') + "</option></select></p>" +
                            "<p style='padding: 5px 0px 5px 0px;'>所属产品线:<select id='productline_code' style='width:174px;'><option value='+" + (equipment.productLine && equipment.productLine.code || '') + "'>" + (equipment.productLine && equipment.productLine.name || '') + "</option></select></p>" +
                            "<p style='padding: 5px 0px 5px 0px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;巡检人:<select id='user_code' style='width:174px;'><option value='+" + (equipment.user && equipment.user.code || '') + "'>" + (equipment.user && equipment.user.name || '') + "</option></select></p>" +
                            "</div>" +
                            "</div>",
                        area: ['350px', '260px'],
                        btn: ['确认', '取消'],
                        offset: ['40%', '45%'],
                        yes: function (index) {
                            var code = $('#code').val()
                            var name = $('#name').val()
                            var department_code = $('#department_code').val()
                            var productline_code = $('#productline_code').val()
                            var user_code = $('#user_code').val()
                            $.post(home.urls.equipment.update(), {
                                code: code,
                                name: name,
                                departmentCode: department_code,
                                productLineCode: productline_code,
                                inspectorCode: user_code
                            }, function (result) {
                                layer.msg(result.message, {
                                    offset: ['40%', '55%'],
                                    time: 700
                                })
                                if (result.code === 0) {
                                    var time = setTimeout(function () {
                                        equipment_manage.init()
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
                    var department_select = $("#department_code");
                    department_select.empty();
                    equipment_manage.department_result.forEach(function (department) {
                        var option = $("<option>").val(department.code).text(department.name);
                        department_select.append(option);

                        if (equipment && equipment.department && department.code == equipment.department.code) {
                            department_select.val(department.code)
                        }
                    });

                    var productline_select = $("#productline_code");
                    productline_select.empty();
                    equipment_manage.productline_result.forEach(function (productline) {
                        var option = $("<option>").val(productline.code).text(productline.name);
                        productline_select.append(option);

                        if (equipment && equipment.department && productline.code == equipment.productLine.code) {
                            productline_select.val(productline.code)
                        }
                    });

                    var user_select = $("#user_code");
                    user_select.empty();
                    equipment_manage.user_result.forEach(function (user) {
                        var option = $("<option>").val(user.code).text(user.name);
                        user_select.append(option);

                        if (equipment && equipment.inspector && user.code == equipment.inspector.code) {
                            user_select.val(user.code)
                        }
                    });
                })
            })
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                // $('#checkAll').prop('checked', false)
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.stdno) + "</td>" +
                    "<td>" + (e.stdname) + "</td>" +
                    "<td>" + (e.roomerea) + "</td>" +
                    "<td>" + (e.bedno) + "</td>" +
                    "<td>" + (e.equip1) + "</td>" +
                    "<td>" + (e.equip2) + "</td>" +
                    "</tr>")
            }) //$数据渲染完毕
            // var editBtns = $('.editequipment')
            // var deleteBtns = $('.deleteequipment')
            // equipment_manage.funcs.bindDeleteEventListener(deleteBtns)
            // equipment_manage.funcs.bindEditEventListener(editBtns)
            // var selectAllBox = $('#checkAll')
            // equipment_manage.funcs.bindSelectAll(selectAllBox, $('.checkbox'))
            //
            // var deleteBatchBtn = $('#model-li-hide-delete-60')
            // equipment_manage.funcs.bindDeleteBatchEventListener(deleteBatchBtn)
            // var checkboxes = $('.checkbox')
            // equipment_manage.funcs.disselectAll(checkboxes, selectAllBox)
        },
        disselectAll: function (checkboxes, selectAllBox) {
            checkboxes.off('change')
            checkboxes.on('change', function () {
                var statusNow = $(this).prop('checked')
                if (statusNow === false) {
                    selectAllBox.prop('checked', false)
                } else if (statusNow === true && $('.checkbox:checked').length === equipment_manage.pageSize) {
                    selectAllBox.prop('checked', true)
                }
            })
        }
    }
}