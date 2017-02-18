/**
 * Created by cookie on 2017/1/27.
 */

$(function () {
    $('#tb-data-aipcase').bootstrapTable({
        sidePagination:"server",//在服务器端分页
        url: "./caselist.json",
        method:"post",
        dataType: "json",
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        striped: true,
        toolbar: '#exampleToolbar',
        iconSize: 'outline',
        pagination:true,
        pageSize: 5,
        pageNumber: 1,
        pageList: [5,20,30],
        contentType: "application/x-www-form-urlencoded",
        queryParams:queryParams,
        icons: {
            refresh: 'glyphicon-repeat',
            toggle: 'glyphicon-list-alt',
            columns: 'glyphicon-list'
        }
    });

    // 配置查询参数
    function queryParams(params) {
        var f = $("#queryForm").serializeArray();
        var searchConditions = {};
        for(var i = 0;i< f.length;i++){
            searchConditions[f[i].name] = f[i].value;
        }
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.limit ,   //页面大小
            pageNo: (params.offset + params.limit)/params.limit,  //页码
            sort: params.sort,//排序列
            order: params.order,//降or升序
            search: params.search// bootstrap-table自带的搜索栏
        };
        $.extend(temp,searchConditions);
        return temp;
    }
    
});

var caseQuery = {};
//
caseQuery.columnFmter= function (value, row, index) {
    return '<a onclick="caseQuery.detail('+row.id+')">查看</a> &nbsp;|&nbsp;' +
           '<a onclick="caseQuery.delete('+row.id+')">删除</a>';
};

caseQuery.detail = function (id) {
    parent.tabTools.addTab({url:'aa',index:2});
};

//删除案件
caseQuery.delete = function (id) {
    layer.confirm('确定要删除案件？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        caseQuery.doDelete(id);
    }, function(){
        layer.msg(id, {
            time: 20000, //20s后自动关闭
            btn: ['明白了', '知道了']
        });
    });
};

//确定删除
caseQuery.doDelete = function (id) {
    $.get("", function (result) {
        if(result == true){
            layer.msg("删除成功。", {icon: 1});
        }else{
            layer.msg("删除失败。", {icon: 2});
        }
    });
};

caseQuery.search = function () {
    $('#tb-data-aipcase').bootstrapTable('refresh');
};

caseQuery.reset = function () {
    var resetArr = $('#queryForm').find(":text");
    for(var i=0; i<resetArr.length; i++){
        resetArr.eq(i).val("");
    }
};
