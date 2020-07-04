let setHref = function (nextNum) {
    let itemNum = $('#span-item-num').val();
    $('#a-bag').attr('href', "/api/v1/insert?item-num=" + itemNum +"&count=" +nextNum);
}
let detail = {
    init: function () {
        let count = $('#input-count');
        $('#btn-count-plus').on('click', function () {
            let nextNum = parseInt($('#input-count').val()) + 1;
            count.val(nextNum);
            setHref(nextNum);

        });
        $('#btn-count-minus').on('click', function () {
            let nextNum = parseInt($('#input-count').val()) - 1;
            if (nextNum >= 1){
                count.val(nextNum);
                setHref(nextNum);
            }
        })
        setHref(1);
    }
}
detail.init();