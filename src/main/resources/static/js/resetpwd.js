$(function(){
    $('#submit_2').on('click', function(){
        var password = $("#password").val();
        if(password.isEmpty()){
            showErrorTip("请输入新密码!");
            return;
        }

        var repassword = $("#repassword").val();
        if(repassword.isEmpty()){
            showErrorTip("请重新输入新密码！");
            return;
        }
        if(repassword != password){
            showErrorTip("两次密码输入不一致！");
            return;
        }
        var phone = $("#phone").val();
        $.ajax({
            url:"resetPassword",
            data:{"password":repassword,"repassword":repassword, "phone":phone},
            success:function(result){
                if(result.success){
                    window.location.href= contextRoot + 'resetPasswordSuccessPage';
                }else{
                    window.location.href=contextRoot + "resetPasswordErrorPage";
                }
            }
        });
    });
});