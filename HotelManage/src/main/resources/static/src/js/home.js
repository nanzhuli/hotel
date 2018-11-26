var home = {
    urls: {
        host: "http://localhost:8080",
        roomStd: {
            getStd: function () {
                return home.urls.host + "/standard";
            }
        }
    }
}