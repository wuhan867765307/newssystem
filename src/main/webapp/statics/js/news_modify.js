var path=$("#path").val();
$(document).ready(function(){
	
	//获取所有新闻主题列表信息
	initTitle();
	
	//根据nid获取新闻信息
	getNewsByNid();
	
});

/**
 * 获取所有新闻主题列表信息
 * @returns
 */
function initTitle(){
	$.ajax({
		url:path+"/TopicServlet",
		data:{method:"getAllTopic"},
		type:"get",
		dataType:"json",
		success:function(data){
			for(var i=0;i<data.length;i++){
				var li=$("<option value='"+data[i].tid+"'>"+data[i].tname+"</option>");
				$("#opt_area .topics").append(li);
			}
		}
	});
}


