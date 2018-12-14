var garageHistory = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            garageHistory.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.garageHistory.getAll, {}, function (result) {

                var data = result.data;        //获取数据
                var $tbody = $("#garageHistory_table").children('tbody');
                garageHistory.funcs.renderHandler($tbody, data);
                var searchBtn = $('#search');
                garageHistory.funcs.bindSearchEventListener(searchBtn, $tbody);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            garageHistory.funcs.bindRefreshEventListener(refreshBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.garagehistoryno) + "</td>" +
                    "<td>" + (e.garageid) + "</td>" +
                    "<td>" + home.vars.garageType[e.type] + "</td>" +
                    "<td>" + home.funcs.timeStr(e.starttime) + "</td>" +
                    "<td>" + home.funcs.timeStr(e.endtime) + "</td>" +
                    "<td>" + (e.brand) + "</td>" +
                    "<td>" + (e.price) + "</td>" +
                    "</tr>")
            });
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
                        garageHistory.init();
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
                $.post(home.urls.garageHistory.getAll, {}, function (result) {
                    $tbody.empty();
                    var data = result.data;
                    for (var i = 0; i < data.length; i++) {
                        var flag = 0;
                        switch (parseInt(select)) {
                            case 0:
                                flag = (JSON.stringify(data[i].brand).indexOf(search) !== -1);
                                break;
                        }
                        if (flag) {
                            $tbody.append(
                                "<tr>" +
                                "<td>" + (data[i].garagehistoryno) + "</td>" +
                                "<td>" + (data[i].garageid) + "</td>" +
                                "<td>" + home.vars.garageType[data[i].type] + "</td>" +
                                "<td>" + home.funcs.timeStr(data[i].starttime) + "</td>" +
                                "<td>" + home.funcs.timeStr(data[i].endtime) + "</td>" +
                                "<td>" + (data[i].brand) + "</td>" +
                                "<td>" + (data[i].price) + "</td>" +
                                "</tr>")
                        }
                    }
                })
            })
        }
    }
};