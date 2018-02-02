var id = $("#id").val();
//预览图片
$("#imgList li").on("click",function () {
    var $this = $(this);
    $("#gallery").css("display", 'block');
    $('#galleryImg').css('background-image',$this.css('background-image')).attr("data",$this.attr("data"));
});

$("#imgList2 li").on("click",function () {
    var $this = $(this);
    $("#gallery2").css("display", 'block');
    $('#galleryImg2').css('background-image',$this.css('background-image')).attr("data",$this.attr("data"));
});

$("#imgList3 li").on("click",function () {
    var $this = $(this);
    $("#gallery3").css("display", 'block');
    $('#galleryImg3').css('background-image',$this.css('background-image')).attr("data",$this.attr("data"));
});

//隐藏预览
$("#galleryImg").on("click",function () {
    $("#gallery").fadeOut(100);
});
$("#galleryImg2").on("click",function () {
    $("#gallery2").fadeOut(100);
});
$("#galleryImg3").on("click",function () {
    $("#gallery3").fadeOut(100);
});


//删除图片
$("#gallery .weui-gallery__del").on("click",function () {
    var data = $("#galleryImg").attr("data");
    $('#imgList li').each(function () {
        var $this = $(this);
        if($this.attr("data") == data){
            removePic(data);
            $this.remove();
        }
    });
    $("#gallery").fadeOut(100);
});

$("#gallery2 .weui-gallery__del").on("click",function () {
    var data = $("#galleryImg2").attr("data");
    $('#imgList2 li').each(function () {
        var $this = $(this);
        if($this.attr("data") == data){
            removePic(data);
            $this.remove();
        }
    });
    $("#gallery2").fadeOut(100);
});

$("#gallery3 .weui-gallery__del").on("click",function () {
    var data = $("#galleryImg3").attr("data");
    $('#imgList3 li').each(function () {
        var $this = $(this);
        if($this.attr("data") == data){
            removePic(data);
            $this.remove();
        }
    });
    $("#gallery3").fadeOut(100);
});
/*************小区正门照片上传实例*************/
var uploader01 = WebUploader.create({
    auto: true,
    swf: '/statics/webuploader-0.1.5/Uploader.swf',
    server: contextRoot + "pictures/uploadPic",
    pick: '#imgInput',
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    },
    method: 'POST',
    formData: {'customerId':id, 'picType': 'entrance'}
});
uploader01.on('uploadStart',function(file) {
    this.options.formData.key='image/'+new Date().getTime()+'/'+file.name.replace(/,/g, '');
});
uploader01.on('fileQueued', function(file) {
    //将上传的图片放到上传列表中
    var $preview = $('<li id="'+file.id+'" class="weui-uploader__file weui-uploader__file_status"><div class="weui-uploader__file-content">0%</div></li>');
    $('#imgList').append($preview);
    //生成缩略图
    uploader01.makeThumb(file, function( error, src) {
        $('#'+file.id).css('background-image','url('+src+')');
    }, 1000, 1000 );

    //隐藏预览
    $("#galleryImg").on("click",function () {
        $("#gallery").fadeOut(100);
    });
});
uploader01.on('uploadProgress', function(file, percentage ) {
    //显示上传进度
    $('#'+file.id).find('.weui-uploader__file-content').html(percentage+'%');
});
uploader01.on('uploadSuccess', function(file, response, src) {
    //设置后台返回的图片id、删除上传进度
    $('#'+file.id).attr("data", response.picId).removeClass('weui-uploader__file_status')
        .find('.weui-uploader__file-content').remove();
    //图片预览
    $('#'+file.id).bind('click',function(){
        $("#gallery").css("display", 'block');
        $('#galleryImg').css('background-image',$('#' + file.id).css('background-image'));
    });
    //删除图片
    $("#gallery .weui-gallery__del").on("click",function () {
        var galleryImg = $("#galleryImg").attr("style");
        $('#imgList li').each(function () {
            var $this = $(this);
            if($this.attr("style") == galleryImg){
                removePic($this.attr("data"));
                $this.remove();
            }
        });
        $("#gallery").fadeOut(100);
    });
});




/*************单元楼照片上传实例*************/
var uploader02 = WebUploader.create({
    auto: true,
    swf: '/statics/webuploader-0.1.5/Uploader.swf',
    server: contextRoot + "pictures/uploadPic",
    pick: '#imgInput2',
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    },
    method: 'POST',
    formData: {'customerId':id, 'picType': 'unit'}
});
uploader02.on('uploadStart',function(file) {
    this.options.formData.key='image/'+new Date().getTime()+'/'+file.name.replace(/,/g, '');
});
uploader02.on('fileQueued', function(file) {
    var $preview = $('<li id="'+file.id+'" class="weui-uploader__file weui-uploader__file_status"><div class="weui-uploader__file-content">0%</div></li>');
    $('#imgList2').append($preview);
    uploader02.makeThumb(file, function( error, src) {
        $('#'+file.id).css('background-image','url('+src+')');
    }, 1000, 1000 );
    $("#galleryImg2").on("click",function () {
        $("#gallery2").fadeOut(100);
    });
});
uploader02.on('uploadProgress', function(file, percentage ) {
    $('#'+file.id).find('.weui-uploader__file-content').html(percentage+'%');
});
uploader02.on('uploadSuccess', function(file, response, src) {
    $('#'+file.id).attr("data", response.picId).removeClass('weui-uploader__file_status')
        .find('.weui-uploader__file-content').remove();
    $('#'+file.id).bind('click',function(){
        $("#gallery2").css("display", 'block');
        $('#galleryImg2').css('background-image',$('#' + file.id).css('background-image'));
    });
    $("#gallery2 .weui-gallery__del").on("click",function () {
        var galleryImg = $("#galleryImg2").attr("style");
        $('#imgList2 li').each(function () {
            var $this = $(this);
            if($this.attr("style") == galleryImg){
                removePic($this.attr("data"));
                $this.remove();
            }
        });
        $("#gallery2").fadeOut(100);
    });

});
/*************客户门牌照片上传实例*************/
var uploader03 = WebUploader.create({
    auto: true,
    swf: '/statics/webuploader-0.1.5/Uploader.swf',
    server: contextRoot + "pictures/uploadPic",
    pick: '#imgInput3',
    accept: {
        title: 'Images',
        extensions: 'gif,jpg,jpeg,bmp,png',
        mimeTypes: 'image/*'
    },
    method: 'POST',
    formData: {'customerId':id, 'picType': 'door'}
});
uploader03.on('uploadStart',function(file) {
    this.options.formData.key='image/'+new Date().getTime()+'/'+file.name.replace(/,/g, '');
});
uploader03.on('fileQueued', function(file) {
    var $preview = $('<li id="'+file.id+'" class="weui-uploader__file weui-uploader__file_status"><div class="weui-uploader__file-content">0%</div></li>');
    $('#imgList3').append($preview);
    uploader03.makeThumb(file, function( error, src) {
        $('#'+file.id).css('background-image','url('+src+')');
    }, 1000, 1000 );
    $("#galleryImg3").on("click",function () {
        $("#gallery3").fadeOut(100);
    });
});
uploader03.on('uploadProgress', function(file, percentage ) {
    $('#'+file.id).find('.weui-uploader__file-content').html(percentage+'%');
});
uploader03.on('uploadSuccess', function(file, response) {
    $('#'+file.id).attr("data", response.picId).removeClass('weui-uploader__file_status')
        .find('.weui-uploader__file-content').remove();
    $('#'+file.id).bind('click',function(){
        $("#gallery3").css("display", 'block');
        $('#galleryImg3').css('background-image',$('#' + file.id).css('background-image'));
    });
    $("#gallery3 .weui-gallery__del").on("click",function () {
        var galleryImg = $("#galleryImg3").attr("style");
        $('#imgList3 li').each(function () {
            var $this = $(this);
            if($this.attr("style") == galleryImg){
                removePic($this.attr("data"));
                $this.remove();
            }
        });
        $("#gallery3").fadeOut(100);
    });
});


function removePic(picId){
    $.ajax({
        url: contextRoot + "pictures/removePic",
        data: {"id": picId},
        success: function (data) {
            if (data.success) {
                alertToast("图片删除成功!");
            } else {
                showErrorTip(data.msg);
            }
        }
    });
}