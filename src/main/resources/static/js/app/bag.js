let bag = {
    init : function() {
        $('#btn-buy').on('click', function () {
            $.ajax({
                type: 'POST',
                url: '/api/v1/buy',
                dataType: 'json',
                contentType: 'application/json; charset = utf-8',
            }).done(function () {
                alert('구매해 주셔서 감사합니다 . ^^ ');
            }).fail(function (error) {
                alert(error['responseJSON']['message']);
            })


        })
    }
}
bag.init();