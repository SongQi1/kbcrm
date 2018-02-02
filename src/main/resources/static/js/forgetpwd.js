$(function(){
    $('#submit_1').on('click', function(){
        var account = $("#account").val();
        if(account.isEmpty()){
            showErrorTip("请输入登录名!");
            return;
        }
        if(checkNumber(account) && ! checkCellphone(account)){
            showErrorTip("手机号码不正确!");
            return;
        }
        var idCard = $("#idCard").val();
        if(idCard.isEmpty()){
            showErrorTip("请输入身份证号！");
            return;
        }
        if(! validateIdCard(idCard)){
            showErrorTip("身份证号不正确！");
            return;
        }

        $.ajax({
            url:"forgetPassword",
            data:{"account":account,"idCard":idCard},
            success:function(result){
                if(result.success){
                    window.location.href= contextRoot +'resetPasswordPage?phone='+result.data;
                }else{
                    warnToast(result.msg);
                }
            }
        });
    });
});