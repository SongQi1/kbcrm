var initParams = {
    ajax:function(data, callback, settings) {
        //封装请求参数
        var param = searchManage.getQueryCondition(data);
        $.ajax({
            type: "GET",
            url: contextRoot + "customer/list",
            cache : false,  //禁用缓存
            data: param,    //传入已封装的参数
            dataType: "json",
            success: function(result) {
                //异常判断与处理
                if (result.success != 'success') {
                    layer.msg("查询失败");
                    return;
                }
                //封装返回数据
                var returnData = {};
                returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                returnData.recordsTotal = result.iTotalRecords;//总记录数
                returnData.recordsFiltered = result.iTotalDisplayRecords;//后台不实现过滤功能，每次查询均视作全部结果
                returnData.data = result.data;
                //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                callback(returnData);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("查询失败");
            }
        });
    },
    columns: [
        {"data": "namePinyin"},
        {"data": "phone"},
        {
            "data": "createTime",
            "defaultContent": ""

        },
        {"data": "remark"}
        ,
        {
            "data": "id",
            "orderable": false,
            "width": "20%",
            "render": function (data, type, row, meta) {
                var html = '<button type="button" class="btn btn-warning" data-dismiss="modal" onclick="viewRow(\'' + data + '\')">详情</button>&nbsp;&nbsp;&nbsp;';
                html += '<button type="button" class="btn btn-primary" onclick="delRow(\'' + data + '\')">删除</button>';
                return html;
            }
        }
    ],
    searching: false,
    serverSide: true,   //启用服务器端分页
    oLanguage: {
        "sLengthMenu": "_MENU_ 记录/页",
        "sEmptyTable": "表中数据为空",
        "sInfo": "从_START_ 到 _END_ 条记录——总记录数为 _TOTAL_ 条",
        "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
        "oPaginate": {
            "sFirst": "第一页",
            "sPrevious": " 上一页 ",
            "sNext": " 下一页 ",
            "sLast": " 最后一页 "
        }
    }
};

var searchManage = {
    getQueryCondition : function(data) {
        var param = {};
        param.phone = $("#phone").val();//查询条件
        param.keyword = $("#keyword").val();//查询条件
        param.startTime = $("#startTime").val();//查询条件
        param.endTime = $("#endTime").val();//查询条件
        param.businessMan = $("#businessMan").val();//业务员信息
        param.status = $("#status").val();//客户状态
        //组装分页参数
        param.start = data.start;
        param.length = data.length;
        param.draw = data.draw;
        return param;
    }
};

/**
 * 表格初始化
 * @returns {*|jQuery}
 */
function initTable() {
    var table = $('#dataTables-example').dataTable(initParams);
    return table;
}

/**
 * 表格刷新
 */
function search() {
    dataTable.fnDestroy();
    dataTable = $('#dataTables-example').dataTable(initParams);
}

/**
 * 删除行
 * @param id
 */
function delRow(id) {
    layer.confirm("确认要删除吗？",{
        btn: ['确认','关闭'] //按钮
    }, function(){
        $.ajax({
            url : contextRoot + "customer/del/"+id,
            type: "POST",
            success:function (result) {
                if (result.httpCode == 200) {
                    layer.msg("删除成功");
                    search();
                } else {
                    layer.alert(result.msg);
                }
            }
        });
    })
}

/**
 * 查看详情
 * @param id
 */
function viewRow(id) {
    window.location.href = contextRoot + "customer/toDetail/"+id;
}


$(function () {
    $('#dateTimeRange').daterangepicker({
        applyClass: 'btn-sm btn-success',
        cancelClass: 'btn-sm btn-default',
        locale: {
            applyLabel: '确认',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '自定义',
            firstDay: 1
        },
        ranges: {
            //'最近1小时': [moment().subtract('hours',1), moment()],
            '今日': [moment().startOf('day'), moment()],
            '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
            '最近7日': [moment().subtract('days', 6), moment()],
            '最近30日': [moment().subtract('days', 29), moment()],
            '本月': [moment().startOf("month"), moment().endOf("month")],
            '上个月': [moment().subtract(1, "month").startOf("month"), moment().subtract(1, "month").endOf("month")]
        },
        opens: 'right', // 日期选择框的弹出位置
        separator: ' 至 ',
        showWeekNumbers: true, // 是否显示第几周

        format: 'YYYY-MM-DD'
    }, function (start, end, label) { // 格式化日期显示框
        $('#startTime').val(start.format('YYYY-MM-DD'));
        $('#endTime').val(end.format('YYYY-MM-DD'));
    })
        .next().on('click', function () {
        $(this).prev().focus();
    });
});

/**
 * 清除时间
 */
function begin_end_time_clear() {
    $('#dateTimeRange').val('');
    $('#startTime').val('');
    $('#endTime').val('');
}