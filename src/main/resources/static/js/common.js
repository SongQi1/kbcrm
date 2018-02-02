//扩展js中String对象的方法
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.isEmpty= function(){
    var regu = /^\s*$/;
    var reg = new RegExp(regu);
    return reg.test(this) || this == "";
};
String.prototype.isNotEmpty = function(){
    return !this.isEmpty();
};
String.prototype.length = function(){
    var w = 0;
    for(var i=0; i<this.length; i++){
        var c = this.charCodeAt(i);
        //单字节加1
        if((c >= 0x0001 && c <=0x007e) || (0xff60 <= c && c<= 0xff9f)){
            w++;
        }else{
            w += 2;
        }
    }
    return w;
};

/**
 * 验证手机号码
 *
 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
 * 联通号码段:130、131、132、136、185、186、145
 * 电信号码段:133、153、180、189
 *
 * @param cellphone
 * @return
 */
function checkCellphone(cellphone) {
    var regex = /^1[34578]\d{9}$/;
    return new RegExp(regex).test(cellphone);
}


/**
 * 判断是否是数字
 * @param text
 * @returns {boolean}
 */
function checkNumber(text){
    var reg ="^\\d*$"
    return new RegExp(reg).test(text);

}

/*
 * 身份证15位编码规则：dddddd yymmdd xx p
 * dddddd：6位地区编码
 * yymmdd: 出生年(两位年)月日，如：910215
 * xx: 顺序编码，系统产生，无法确定
 * p: 性别，奇数为男，偶数为女
 *
 * 身份证18位编码规则：dddddd yyyymmdd xxx y
 * dddddd：6位地区编码
 * yyyymmdd: 出生年(四位年)月日，如：19910215
 * xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女
 * y: 校验码，该位数值可通过前17位计算获得
 *
 * 前17位号码加权因子为 Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ]
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
 * 如果验证码恰好是10，为了保证身份证是十八位，那么第十八位将用X来代替
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
 * i为身份证号码1...17 位; Y_P为校验码Y所在校验码数组位置
 */
function validateIdCard(idCard){
    //15位和18位身份证号码的正则表达式
    var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

    //如果通过该验证，说明身份证格式正确，但准确性还需计算
    if(regIdCard.test(idCard)){
        if(idCard.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
            }

            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=idCard.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                    return true;
                }else{
                    return false;
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }else{
        return false;
    }
}



/*
 * 全局的ajax访问，处理ajax清求时sesion超时
 */
$.ajaxSetup({
    type : "post",
    dataType : "json",
    contentType : "application/x-www-form-urlencoded;charset=utf-8",
    cache : false,
    beforeSend : function(xhr) {
        loadingToast("数据加载中");
    },
    complete : function(xhr, textStatus) {
        hideLoadingToast();
    }
});


/**
 * 在页面顶端显示错误提示信息
 * @param msg
 */
function showErrorTip(msg){
    var $tooltips = $('.js_tooltips');
    if ($tooltips.css('display') != 'none') return;

    // toptips的fixed, 如果有`animation`, `position: fixed`不生效
    $('.page.cell').removeClass('slideIn');

    $tooltips.html(msg).css('display', 'block');
    setTimeout(function () {
        $tooltips.css('display', 'none').html("");
    }, 2000);
}

function loadingToast(msg){
    var $loadingToast = $('#loadingToast');
    if ($loadingToast.css('display') != 'none') return;
    if(msg.isNotEmpty()){
        $loadingToast.find(".weui-toast__content").html(msg);
    }
    $loadingToast.fadeIn(100);
}

function hideLoadingToast(){
    var $loadingToast = $('#loadingToast');
    $loadingToast.fadeOut(100);

}

/**
 * 弹出成功吐司框
 * @param msg
 */
function alertToast(msg){
    var $toast = $('#toast');
    if ($toast.css('display') != 'none') return;
    $toast.find(".weui-toast__content").html(msg);
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100);
    }, 1000);
}

/**
 * 弹出警告吐司框
 * @param msg
 */
function warnToast(msg) {
    var $toast = $('#warnToast');
    if ($toast.css('display') != 'none') return;
    $toast.find(".weui-toast__content").html(msg);
    $toast.fadeIn(100);
    setTimeout(function () {
        $toast.fadeOut(100);
    }, 2000);

}

/**
 * 弹出确认对话框
 * @param title 标题
 * @param content 内容
 * @param okText 确认按钮文字
 * @param okFunction 确认按钮handler
 * @param cancelText 取消按钮文字
 */
function confirmDialog(title, content, okText, okFunction, cancelText){
    $("#iosDialog1 .weui-dialog__title").html(title);
    $("#iosDialog1 .weui-dialog__bd").html(content);
    var $primary = $("#iosDialog1 .weui-dialog__btn_primary");
    var $default = $("#iosDialog1 .weui-dialog__btn_default");
    $primary.html(okText).on("click", okFunction);
    $default.html(cancelText).on("click", function(){
        $(this).parents('.js_dialog').fadeOut(200);
    });
    $('#iosDialog1').fadeIn(200);
}

/**
 * 隐藏确认对话框
 */
function hideConfirmDialog(){
    $(".js_dialog").fadeOut(200);

}
var contextRoot = $("meta[name='ctx']").attr("content") + "/";



