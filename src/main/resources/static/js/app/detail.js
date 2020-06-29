let detail = {
    init: function () {
        let count = $('#input-count');
        $('#btn-count-plus').on('click', function () {
            let nextNum = parseInt($('#input-count').val()) + 1;
            count.val(nextNum);
        });
        $('#btn-count-minus').on('click', function () {
            let nextNum = parseInt($('#input-count').val()) - 1;
            if (nextNum >= 1){
                count.val(nextNum);
            }
        })
    }
}
detail.init();