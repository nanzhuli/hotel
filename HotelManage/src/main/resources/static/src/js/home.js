var home = {
    urls: {
        host: "http://localhost:8080",
        roomStd: {
            getStd: function () {
                return home.urls.host + "/standard";
            }
        }
    },
    funcs: {
        spaceFunc: function (data) {
            if (data === null || home.funcs.trim(data) === "") {
                return "无";
            }
            else {
                return data;
            }
        },
        trim: function (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }
    }
}