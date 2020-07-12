let register = {
    init: function () {
        let _this = this;
        $('#btn-register').on('click', function () {
            _this.register();
        })
    },
    register: function () {
        let data = {
            userId: $('#user_id').val(),
            password: $('#user_password').val(),
            name: $('#user_name').val(),
            address: $('#user_address').val(),
            phone: $('#user_phone').val(),
            email: $('#user_email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/register',
            dataType: 'json',
            contentType: 'application/json; charset = utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('회원 가입이 완료되었습니다. ');
            window.location.href = '/products';
        }).fail(function (err) {
            alert(err['responseJSON']['message']);
        });
    }
}
register.init();