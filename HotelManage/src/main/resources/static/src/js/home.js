var home = {
    // 全局变量
    vars: {
        type: ["单人间", "双人间", "商务间", "家庭间"],
        window: ["无", "有"],
        typeEvent: ["wrong event type", "1楼", "2楼", "3楼"],
        enter: ["否", "是"],
        member: ["否", "是"],
        gender: ["女", "男"],
        position: ["无", "楼层1", "楼层2", "楼层3"],
        authority: ["Admin", "Worker", "Server"],
        workTime: ["全天", "00:00-11:59", "12:00-23:59"]
    },
    // 请求地址参数
    urls: {
        host: "http://localhost:8080",
        roomInfo: {
            getAll: "/room/roomlist",
            getOne: "/room/searchOne/",  //{}
            add: "/room/add",
            update: "/room/update/",     //{}
            delete: "/room/delete/"      //{}
        },
        order: {
            getAll: "/order/orderlist",
            getOne: "/order/searchOne/",    //orderno
            update: "/order/update/",     // orderno
            delete: "/order/delete/",
            // 结算
            submit: "/order/settle/"    //orderno
        },
        room: { //orderroom
            // orderroom表查看
            getAll: "/order/orderroom/",            // orderno
            //
            getOne: "/order/orderroom/orderroomSearchOne/", //orno
            // orderroom表更新
            update: "/order/orderroom/update/"      // rino
        },
        roomid: {
            // roomid表查看
            getAll: "/order/orderroom/roomid/list/",// roomno
            //
            getOne: "/order/orderroom/roomid/roomidSearchOne/", //rino
            // roomid表修改
            update: "/order/orderroom/roomid/update/",//rino
            // 获取不在roomid中的roomno
            getEmpty: "/order/orderroom/roomid/getEmptyRoomno"
        },
        employee: {
            getAll: "/employ/list",
            getOne: "/employ/searchOne/",//employno
            add: "/employ/add",
            update: "/employ/update/",//employno
            delete: "/employ/delete/"
        },
        event: {
            getAll: "/event/allList",
            getOne: "/event/searchOne/",
            add: "/event/add",
            update: "/event/update/",
            delete: "/event/delete/"
        }
    },
    // 功能型函数
    funcs: {
        spaceFunc: function (data) {
            return (data === null || home.funcs.trim(data) === "") ? "无" : data;
        },
        spaceFuncEmpty: function (data) {
            return (data === null || home.funcs.trim(data) === "") ? "" : data;
        },
        trim: function (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        },
        timeStr: function (str) {
            if (str === null) {
                return "无"
            } else {
                var arr1 = str.split("T");
                var arr2 = arr1[1].split(".");
                str = arr1[0] + " " + arr2[0];
                return str;
            }
        },
        timeStrDate: function (str) {
            if (str === null) {
                return "无"
            } else {
                var arr1 = str.split("T");
                var arr2 = arr1[1].split(".");
                str = arr1[0];
                return str;
            }
        },
        // 将当前值与将显示的值比较
        compareSelect: function (curr, val) {
            return parseInt(curr) === parseInt(val) ? " selected='selected'" : "";
        },
        // 生成 "<option value='0'" + home.funcs.compareSelect(0, res.employworktime) + ">" + home.vars.workTime[0] + "</option>" +
        generateOption: function (number, refVars, displayData) {
            var str = "";
            for (var i = 0; i < number; i++) {
                str += "<option value='";
                str += i;
                str += "'";
                str += home.funcs.compareSelect(i, displayData);
                console.log(displayData);
                str += ">";
                str += refVars[i];
                str += "</option>";
            }
            return str;
        },
        generateSelect: function (number, refVars) {
            var str = "";
            for (var i = 0; i < number; i++) {
                str += "<option value='";
                str += i;
                str += "'>";
                str += refVars[i];
                str += "</option>";
            }
            return str;
        }
    },
    // Get数据型函数
    get: {
        roomType: function (typeNo) {
            $.get()
        }
    }
}