let page ={
    init : function(){
        this.setPageUrl();
        this.setActivePage();
    },
    setPageUrl :function() {
        let parameters = this.getParameters();
        $('.page_nation a').each(function (index, item) {
            if (parameters['category'] === 'undefined' || parameters['category'] == null) {
                $(item).attr('href', '?&page=' + (index + 1));
            } else {
                $(item).attr('href', '?category=' + parameters['category'] + '&page=' + (index + 1));
            }
            $(item).attr('id','page'+(index + 1))
        });
    },
    getParameters : function () {
        let url = document.location.href;
        let qs = url.substring(url.indexOf('?') + 1).split('&');
        for (var i = 0, result = {}; i < qs.length; i++) {
            qs[i] = qs[i].split('=');
            result[qs[i][0]] = decodeURIComponent(qs[i][1]);
        }
        return result;
    },
    setActivePage: function() {
        let parameters = this.getParameters();
        let currentPage = parameters['page'];
        if (currentPage == null){
            currentPage=1;
        }
        $('.page_nation').children('#page'+currentPage).attr('class','active');
    }
}
page.init();

