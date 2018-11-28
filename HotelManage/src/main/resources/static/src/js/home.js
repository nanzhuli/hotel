var home = {
    // 全局变量
    type: ["Single Room", "Double Room", "Business Room", "Family Room"],
    window: ["无", "有"],
    // 请求地址参数
    urls: {
        host: "http://localhost:8080",
        roomInfo: {
            getAll: "/room/roomlist",
            getOne: "/room/searchOne/",  //{}
            add: "/room/add",
            update: "/room/update/",     //{}
            delete: "/room/delete/"      //{}
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
        }
    },
    // Get数据型函数
    get: {
        roomType: function (typeNo) {
            $.get()
        }
    }
}