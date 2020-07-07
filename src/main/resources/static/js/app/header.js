let header =  {
    init : function () {
        let parameter = this.getParameters();
        if (parameter['category']){
            $('#nav_category .active').attr('class', '');
            $('#nav_category a').each(function(index, item){
                console.log($(item).text());
                if ($(item).text().toUpperCase()===parameter['category']){
                    $(item).attr('class', 'active');
                }
            })
        }
    }
    ,
    getParameters : function () {
        let url = document.location.href;
        let qs = url.substring(url.indexOf('?') + 1).split('&');
        for (var i = 0, result = {}; i < qs.length; i++) {
            qs[i] = qs[i].split('=');
            result[qs[i][0]] = decodeURIComponent(qs[i][1]);
        }
        return result;
    },
}
header.init()