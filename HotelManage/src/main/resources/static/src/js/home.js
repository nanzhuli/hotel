var home = {
    // 全局变量
    vars: {
        type: ["单人间", "双人间", "商务间", "家庭间"],
        window: ["无", "有"],
        typeEvent: ["wrong event type", "1楼", "2楼", "3楼"]
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
        event:{
            getAll:"/event/allList",
            getOne:"/event/searchOne/",
            add: "/event/add",
            update:"/event/update/",
            delete:"/event/delete/"
        }
    },
    // 功能型函数
    funcs: {
        spaceFunc: function (data) {
            if (data === null || home.funcs.trim(data) === "") {
                return "无";
            }
            else {
                return data;
            }
        },
        spaceFuncEmpty: function (data) {
            if (data === null || home.funcs.trim(data) === "") {
                return "";
            }
            else {
                return data;
            }
        },
        trim: function (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        },
        timeStr: function (str) {
            if (str === null) {
                return "无"
            }
            else {
                var arr1 = str.split("T");
                var arr2 = arr1[1].split(".");
                str = arr1[0] + " " + arr2[0];
                return str;
            }
        }
    },
    // Get数据型函数
    get: {
        roomType: function (typeNo) {
            $.get()
        }
    }
}