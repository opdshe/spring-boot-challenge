
let login = {
    init: function () {
        let _this = this;
        $('#btn-login').on('click', function () {
            _this.login();
        })
    },
    login: function () {
        let data = {
            userId: $('#user_id').val(),
            password: $('#user_password').val()
        };
        $.ajax({
            type: 'POST',
            url: '/auth/login',
            dataType: 'json',
            contentType: 'application/json; charset = utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('로그인 성공 ');
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}
//login.init();
