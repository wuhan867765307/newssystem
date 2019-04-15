<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<title>编辑主题--管理后台</title>
   <script type="text/javascript">
		function check(){
			var tname = document.getElementById("tname");
	
			if(tname.value == ""){
				alert("请输入主题名称！！");
				tname.focus();
				return false;m
			}		
			return true;
		}
	</script>
    <link href="${pageContext.request.contextPath}/statics/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- 用来保存当前的上下文路径：在js中使用 -->
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
  <div id="main">
      <div>
		  <%@include file="/jsp/newspages/console_element/top.jsp"%>
	  </div> 
      <div id="opt_list">
		  <%@include file="/jsp/newspages/console_element/left.jsp"%>
      </div> 
	  <div id="opt_area">
	    <ul class="classlist">
			<%--ajax异步加载主题数据--%>
			<%--<li> 国内--%>
			    <%--&nbsp;&nbsp;&nbsp;&nbsp; <a href="topic_modify.jsp">修改</a>--%>
			    <%--&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">删除</a>--%>
			<%--</li>--%>
		</ul>
	  </div>
	  <%@include file="/jsp/newspages/console_element/bottom.jsp"%>
  </div>
<script src="${pageContext.request.contextPath }/statics/js/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/topic_list.js"></script>
</body>
</html>	