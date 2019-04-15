//获取当前应用的上下文路径
var path=$("#path").val();
var currentPageNo=1;
var pageList=null;
var totalPageCount=1;
$(document).ready(function(){
	
	//加载第一页的新闻数据
	getPage_news(1);
	
	//下一页
	$("#opt_area").on("click",".nextPage",function(){
		currentPageNo=parseInt($(".currentPageNo").html());
		getPage_news(currentPageNo+1);
	});
	//尾页
	$(".lastPage").click(function(){
		currentPageNo=parseInt($(".currentPageNo").html());
		getPage_news(totalPageCount);
	});
	//上一页
	$(".previousePage").click(function(){
		currentPageNo=parseInt($(".currentPageNo").html());
		getPage_news(currentPageNo-1);
	});
	//首页
	$(".firstPage").click(function(){
		currentPageNo=parseInt($(".currentPageNo").html());
		getPage_news(1);
	});
	
	//ajax删除新闻
	$("#opt_area").on("click",".classlist .del",function(){
		remove_news($(this));
	});
	
	
});

/**
 * 分页获取新闻数据
 * @returns
 */
function getPage_news(currentPageNo){
	$.ajax({
		url:path+"/NewsServlet",
		type:"get",
		data:{method:"getPageNews",currentPageNo:currentPageNo},
		dataType:"json",
		success:function(data){
			//清空所有数据
			$("#opt_area .classlist").html("");
			
			pageList=data.pageList;//获取当前页中的新闻数据
			currentPageNo=data.currentPageNo;		
			totalPageCount=data.totalPageCount;
			
//			alert(currentPageNo+"-"+totalPageCount);
			
			$(".currentPageNo").html(currentPageNo);//为当前页赋值
			$(".totalPageCount").html(totalPageCount);//为总页数赋值
			for(var i=0;i<pageList.length;i++){
				var str=$("<li>"+pageList[i].ntitle
					+"<span> 作者： "+pageList[i].nauthor
					+"&#160;&#160;&#160;&#160; <a href='"+path+"/NewsServlet?method=getNewsByNid&nid="+pageList[i].nid+"'>修改</a>"
					+"&#160;&#160;&#160;&#160; <a class='del' data_nid='"+pageList[i].nid+"' href='javascript:;'>删除</a>"
					+"</span>"
					+"</li>");
				$("#opt_area .classlist").append(str);
				if((i+1)%5==0){
					$("#opt_area .classlist").append($("<li class='space'></li>"));
				}
			}
			
			//先显示所有的页面跳转
			$(".nextPage").show();
			$(".lastPage").show();
			$(".firstPage").show();
			$(".previousePage").show();
			
			//用于对跳转页面的显示进行判断
			if(currentPageNo==totalPageCount){
				$(".nextPage").hide();
				$(".lastPage").hide();
			}
			if(currentPageNo==1){
				$(".firstPage").hide();
				$(".previousePage").hide();
			}

		}
	});
}

/**
 * 删除新闻
 * @returns
 */
function remove_news(obj){
	var nid=obj.attr("data_nid");
	$.ajax({
		url:path+"/NewsServlet",
		type:"get",
		data:{method:"delete",nid:nid},
		dataType:"json",
		success:function(data){
			if(data.flag=="true"){//后台删除成功
				obj.parent().parent().remove();//前台删除该li
				currentPageNo=parseInt($(".currentPageNo").html());
				getPage_news(currentPageNo);//刷新当前页数据
			}
			alert(data.msg);
		}
	});
}



