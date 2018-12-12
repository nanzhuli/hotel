var login = {
    init: function () {
        var loginBtn = $("#loginBtn");
        login.funcs.loginEventListener(loginBtn)
    },
    funcs: {
        loginEventListener: function (loginBtn) {
            loginBtn.off('click');
            loginBtn.on('click', function () {
                console.log("LOGIN");
                var username = $("#username").val();
                var password = $("#password").val();
                console.log(username);
                console.log(password);

            });
        }
    }
};