let login = {
    init: function () {
        let _this = this;
        $('#btn-login').on('click', function () {
            _this.login();
        })
    },
    login: function () {
        let data = {
            memId: $('#user_id').val(),
            password: $('#user_password').val()
        };
    }
}
login.init();