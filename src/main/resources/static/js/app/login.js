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
        }

        $.ajax({
            type: 'POST',
            url: '/auth/login',
            dataType: 'json',
            contentType: 'application/json; charset = utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('로그인이 완료되었습니다. ');
            window.location.href = '/items';
        }).fail(function (url) {
            alert(url.valueOf().toString());
            window.location.href = '/items';
        });
    }
}
login.init();