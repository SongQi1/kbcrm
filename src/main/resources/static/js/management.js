(function ($) {
    /*MENU------------------------------------*/
    $('#main-menu').metisMenu();

    $(window).bind("load resize", function () {
        if ($(this).width() < 768) {
            $('div.sidebar-collapse').addClass('collapse')
        } else {
            $('div.sidebar-collapse').removeClass('collapse')
        }
    });

    $("#sideNav").click(function(){
        if($(this).hasClass('closed')){
            $('.navbar-side').animate({left: '0px'});
            $(this).removeClass('closed');
            $('#page-wrapper').animate({'margin-left' : '260px'});

        }
        else{
            $(this).addClass('closed');
            $('.navbar-side').animate({left: '-260px'});
            $('#page-wrapper').animate({'margin-left' : '0px'});
        }
    });
}(jQuery));

function signOut() {
    layer.confirm("确认要退出吗？",{
        btn: ['确认','关闭'] //按钮
    }, function(){
        $.ajax({
            url:contextRoot + "logout",
            success:function(data){
                window.location.href=contextRoot;
            }
        });
    })
}

