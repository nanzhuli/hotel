var finance = {
    pageSize: 0,
    init: function () {
        layui.use('layer', function () {
            var index = layer.load(2, {
                offset: ['48%', '48%'],
                time: 300,
                anim: -1,
                isOutAnim: false
            });
            finance.funcs.renderTable();
            // layer.close(index);
        });
    },
    funcs: {
        renderTable: function () {
            $.get(home.urls.finance.getAll, {}, function (result) {
                var data = result.data;        //获取数据
                var $tbody = $("#finance-table").children('tbody');
                finance.funcs.renderHandler($tbody, data);
                var searchBtn = $('#search');
                finance.funcs.bindSearchEventListener(searchBtn, $tbody);
            });
            // 数据渲染完毕
            var refreshBtn = $("#refresh");
            finance.funcs.bindRefreshEventListener(refreshBtn);
            var selectBtn = $("#select-bar");
            finance.funcs.bindSelectEventListener(selectBtn);
        },
        renderHandler: function ($tbody, data) {
            $tbody.empty();                  //清空表格
            data.forEach(function (e) {
                $tbody.append(
                    "<tr>" +
                    "<td>" + (e.type) + "</td>" +
                    "<td>" + (e.money) + "</td>" +
                    "<td>" + home.funcs.timeStr(e.time) + "</td>" +
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
                        finance.init();
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
                var date = $('#search-bar-date').val();
                var month = $('#search-bar-month').val();
                var year = $('#search-bar-year').val();
                console.log(year);
                console.log(month);
                console.log(date);
                switch (parseInt(select)) {
                    case 0:
                        $.post(home.urls.finance.getByDay, {
                            day: date,
                            month: month,
                            year: year
                        }, function (result) {
                            console.log(result);
                            $tbody.empty();
                            var data = result.data;
                            data.forEach(function (e) {
                                $tbody.append(
                                    "<tr>" +
                                    "<td>" + (e.type) + "</td>" +
                                    "<td>" + (e.money) + "</td>" +
                                    "<td>" + home.funcs.timeStr(e.time) + "</td>" +
                                    "</tr>")
                            });
                        });
                        break;
                    case 1:
                        $.post(home.urls.finance.getByMonth, {
                            month: month,
                            year: year
                        }, function (result) {
                            $tbody.empty();
                            var data = result.data;
                            data.forEach(function (e) {
                                $tbody.append(
                                    "<tr>" +
                                    "<td>" + (e.type) + "</td>" +
                                    "<td>" + (e.money) + "</td>" +
                                    "<td>" + home.funcs.timeStr(e.time) + "</td>" +
                                    "</tr>")
                            });
                        });
                        break;
                    case 2:
                        $.post(home.urls.finance.getByYear, {
                            year: year
                        }, function (result) {
                            $tbody.empty();
                            var data = result.data;
                            data.forEach(function (e) {
                                $tbody.append(
                                    "<tr>" +
                                    "<td>" + (e.type) + "</td>" +
                                    "<td>" + (e.money) + "</td>" +
                                    "<td>" + home.funcs.timeStr(e.time) + "</td>" +
                                    "</tr>")
                            });
                        });
                        break;
                }

            })
        },
        bindSelectEventListener: function (selectBtn) {
            selectBtn.off('click');
            selectBtn.on('click', function () {
                var select = parseInt($('#select-bar').val());
                var element = $('#change')[0];
                console.log(element);
                switch (select) {
                    case 0:
                        element.innerHTML =
                            "<input type='number' id='search-bar-year' class='search-bar-date' placeholder='年'>" +
                            "<input type='number' id='search-bar-month' class='search-bar-date' placeholder='月'>" +
                            "<input type='number' id='search-bar-date' class='search-bar-date' placeholder='日'>";
                        break;
                    case 1:
                        element.innerHTML =
                            "<input type='number' id='search-bar-year' class='search-bar-date' placeholder='年'>" +
                            "<input type='number' id='search-bar-month' class='search-bar-date' placeholder='月'>";
                        break;
                    case 2:
                        element.innerHTML =
                            "<input type='number' id='search-bar-year' class='search-bar-date' placeholder='年'>";
                        break;
                }
            });
        }
    }
};