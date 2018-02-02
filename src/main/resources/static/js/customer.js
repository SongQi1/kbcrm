/**
 * Description:<p></p>
 * Created by songqi on 2017/9/11.
 */
$(function () {
    $("#customer").on("click",function () {
        var namePinyin = $("#namePinyin").val().trim();
        if(namePinyin.isEmpty()){
            showErrorTip("客户姓名不能为空！");
            return;
        }
        var phone = $("#phone").val().trim();
        if(phone.isEmpty()){
            showErrorTip("客户手机号码不能为空！");
            return;
        }

        if(phone.isNotEmpty() && checkCellphone(phone) == false){
            showErrorTip("手机号码不正确！");
            return;
        }
        
        $.ajax({
            url : contextRoot + "decorationForm/update",
            data:$("#form").serialize(),
            success:function (data) {
                if(data.success){
                    window.location.href=contextRoot + "decorationForm/addFormPage2/"+ data.customerDecorationForm.id
                }else{
                    showErrorTip(data.msg);
                }
            }
        });
    });
    
    $("#saveHouse").on("click",function () {
        var houseArea = $("#houseArea").val().trim();
        if(houseArea.isEmpty()){
            showErrorTip("请输入房屋面积！");
            return;
        }

        $.ajax({
            url : contextRoot + "decorationForm/update",
            data:$("#form").serialize(),
            success:function (data) {
                if(data.success){
                    window.location.href=contextRoot + "decorationForm/addFormPage3/"+ data.customerDecorationForm.id
                }else{
                    showErrorTip(data.msg);
                }
            }

        });
    });

    $("#saveMaterial").on("click",function () {
        $.ajax({
            url : contextRoot + "decorationForm/update",
            data:$("#form").serialize(),
            success:function (data) {
                if(data.success){
                    window.location.href=contextRoot + "decorationForm/addFormPage4/"+ data.customerDecorationForm.id
                }else{
                    showErrorTip(data.msg);
                }
            }
        });
    });


    $('#showDatePicker1').on('focus', function () {
        $this = $(this);
        weui.datePicker({
            start:new Date().getFullYear(),
            end:new Date().getFullYear()+3,
            defaultValue:[new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()+1],
            onChange: function (result) {
                console.log(result);
            },
            onConfirm: function (result) {
                $this.val(result[0]+'-'+result[1]+'-'+result[2]);
                console.log(result);
            }
        });
    });
    $('#showDatePicker2').on('focus', function () {
        $this = $(this);
        weui.datePicker({
            start:new Date().getFullYear(),
            end:new Date().getFullYear()+3,
            defaultValue:[new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()+1],
            onChange: function (result) {
                console.log(result);
            },
            onConfirm: function (result) {
                $this.val(result[0]+'-'+result[1]+'-'+result[2]);
                console.log(result);
            }
        });
    });
});
