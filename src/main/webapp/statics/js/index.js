//获取当前应用的上下文路径
var path=$("#path").val();
var currentPageNo=1;
var pageList=null;
var totalPageCount=1;
var tid=-1;
$(document).ready(function(){

    //加载第一页的新闻数据
    getPage_news(1);

    //下一页
    $(".main").on("click",".nextPage",function(){
        currentPageNo=parseInt($(".currentPageNo").html());
        getPage_news(currentPageNo+1);
    });
    //尾页
    $(".main").on("click",".lastPage",function(){
        currentPageNo=parseInt($(".currentPageNo").html());
        getPage_news(totalPageCount);
    });
    //上一页
    $(".main").on("click",".previousePage",function(){
        currentPageNo=parseInt($(".currentPageNo").html());
        getPage_news(currentPageNo-1);
    });
    //首页
    $(".main").on("click",".firstPage",function(){
        currentPageNo=parseInt($(".currentPageNo").html());
        getPage_news(1);
    });

    //加载新闻主题
    getTopics();

    // //点击主题加载相应新闻
    // $(".main").on("click",".clickTopic",function(){
    //     currentPageNo=parseInt($(".currentPageNo").html());
    //     tid=$(".main .class_date .clickTopic").attr("data-tid");
    //     clickTopic(currentPageNo,tid);
    // })


});

/**
 * 分页获取新闻数据
 * @returns
 */
function getPage_news(currentPageNo){
    $.ajax({
        url:path+"/NewsServlet",
        type:"get",
        data:{method:"getPageNews",currentPageNo:currentPageNo,pageSize:20},
        dataType:"json",
        success:function(data){
            //清空所有数据
            $(".main .classlist").html("");

            pageList=data.pageList;//获取当前页中的新闻数据
            currentPageNo=data.currentPageNo;
            totalPageCount=data.totalPageCount;

//			alert(currentPageNo+"-"+totalPageCount);

            $(".currentPageNo").html(currentPageNo);//为当前页赋值
            $(".totalPageCount").html(totalPageCount);//为总页数赋值
            for(var i=0;i<pageList.length;i++){
                var str=$("<li>"+pageList[i].ntitle
                    +"<span> 作者： "+pageList[i].nauthor
                    +"&#160;&#160;&#160;&#160; <a href='"+path+"/NewsServlet?method=showNewsByNid&nid="+pageList[i].nid+"'>查看</a>"
                    +"</span>"
                    +"</li>");
                $(".main .classlist").append(str);
                if((i+1)%5==0){
                    $(".main .classlist").append($("<li class='space'></li>"));
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
 * 获取新闻主题列表
 */
function getTopics(){
    $.ajax({
        url:path+"/TopicServlet",
        type:"get",
        data:{method:"getAllTopic"},
        dataType:"json",
        success:function(data){
            //清空所有数据
            $(".main .class_date .class_month").html("");

            for(var i=0;i<data.length;i++){
                var str=$("<a href='javascript:;' data-tid='"+data[i].tid+"'><b> "+data[i].tname+" </b></a>");
                    $(".main .class_date .class_month").append(str);
                }
            }
        })
}



