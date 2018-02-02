$(function(){

    $('#login').on('click', function(){
        var account = $("#account").val().trim();
        var password = $("#password").val().trim();
        if(account.isEmpty()){
            layer.msg("请输入登录名。");
            return;
        }
        if(password.isEmpty()){
            layer.msg("请输入密码");
            return;
        }
        $.ajax({
            url: contextRoot +"login",
            data:{
                "account": account,
                "password": password
            },
            success:function (data) {
                if(data.success){
                    var userType = data.data.userType;
                    if(userType == 1){
                        window.location.href = contextRoot + "userHomePage";
                    }else if(userType == 2){
                        window.location.href= contextRoot + "user/toList";
                    }
                }else{
                    layer.msg(data.msg);
                }
            }
        })
    });


    $("#regin").on("click",function () {

        var phone = $("#phone").val().trim();
        if(phone.isEmpty()){
            showErrorTip("请输入手机号!");
            return false;
        }
        if(checkNumber(phone) && ! checkCellphone(phone)){
            showErrorTip("手机号码不正确!");
            return false;
        }
        var password = $("#password").val().trim();
        if(password.isEmpty()){
            showErrorTip("请输入密码！");
            return;
        }
        var repassword = $("#repassword").val().trim();
        if(repassword.isEmpty()){
            showErrorTip("请重新输入密码！");
            return;
        }
        if(repassword != password){
            showErrorTip("两次密码不一致，请重新输入！");
            return;
        }
        $.ajax({
            url:"regin",
            data:{"phone":phone,"password":password},
            success:function(data){
                if(data.success){
                    var userType = data.data.userType;
                    if(userType == 1){
                        window.location.href=contextRoot + "userHomePage";
                    }else if(userType == 2){
                        window.location.href= contextRoot + "managerHomePage";
                    }
                }else{
                    warnToast(data.msg);
                }
            }
        });

    });


    $("#logout").on("click",function () {
        function logoutAction(){
            $.ajax({
                url:contextRoot + "logout",
                success:function(data){
                    window.location.href=contextRoot;
                }
            });
        }
        confirmDialog("提醒","确定要退出吗？", "确定", logoutAction, "取消", null);
    })
});