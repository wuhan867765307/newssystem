//获取当前应用的上下文路径
var path=$("#path").val();
$(document).ready(function () {
    //获取所有新闻主题列表信息
    getAllTopic();

    //删除新闻
    $("#opt_area").on("click","#deltopic",function () {
        var tid=$(this).attr("data-tid");
        var delObj=$(this).parent();
        deleteTopicByTid(tid,delObj);
    })
})

/**
 * 删除新闻
 * @returns
 */
function deleteTopicByTid(tid,delObj) {
    $.ajax({
        url:path+"/TopicServlet",
        data: {method: "deleteTopicByTid",tid:tid},
        type: "get",
        dataType: "json",
        success: function (data) {
            if(data.flag=="true"){//删除成功！
                $(delObj).remove();
                alert("删除成功！");
            }else if(data.flag=="false"){//删除失败！
                alert("删除失败！");
            }else if(data.flag=="error"){//删除异常！
                alert("删除异常！");
            }
        }
    });
}

/**
 * 获取所有新闻主题列表信息
 * @returns
 */
function getAllTopic() {
    $.ajax({
        url:path+"/TopicServlet",
        data: {method: "getAllTopic"},
        type: "get",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var li = $('<li> '+data[i].tname+'&nbsp;&nbsp;&nbsp;&nbsp; ' +
                    '<a href='+path+'/TopicServlet?method=getTopicByTid&tid='+data[i].tid+'&tname='+data[i].tname+'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp; ' +
                    '<a id="deltopic" data-tid='+data[i].tid+' href="javascript:;">删除</a></li>');
                $("#opt_area .classlist").append(li);
            }
        }
    });
}