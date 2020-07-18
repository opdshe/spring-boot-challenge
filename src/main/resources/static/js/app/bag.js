let bag = {
    init: function () {
        let _this = this;
        $('#btn-buy').on('click', function () {
            _this.buy();
        })
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
    },
    buy : function () {
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
    },
    delete: function () {
        let removalObjects = [];
        $('input:checkbox[name="bag-checkbox"]').each(function () {
            if (this.checked) {
                removalObjects.push(this.value);
            }
        });
        $.ajax({
            type: 'POST',
            url: '/api/v1/bag/delete',
            dataType: 'text',
            data: {
                removalObjects:removalObjects
            }
        }).done(function () {
            alert('해당 상품을 장바구니에서 삭제하였습니다. ');
            window.location.href = '/bag';
        }).fail(function (err) {
            alert(err['responseJSON']['message']);
        });
    }
}
bag.init();

