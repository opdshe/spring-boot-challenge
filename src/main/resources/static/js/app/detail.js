let detail = {
    init: function () {
        let _this = this;
        let count = $('#input-count');
        $('#btn-count-plus').on('click', function () {
            let nextNum = parseInt($('#input-count').val()) + 1;
            count.val(nextNum);

        });
        $('#btn-count-minus').on('click', function () {
            let nextNum = parseInt($('#input-count').val()) - 1;
            if (nextNum >= 1) {
                count.val(nextNum);
            }
        })
        $('#btn-bag').on('click', function () {
            _this.insert();
        })
    },
    insert: function () {
        let data = {
            id: $('#span-item-num').val(),
            count: $('#input-count').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/insert',
            dataType: 'json',
            contentType: 'application/json; charset = utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('해당 상품이 장바구니에 등록 되었습니다. ');
        }).fail(function (error) {
            alert(error['responseJSON']['message']);
        });
    }
}
detail.init();