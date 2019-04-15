<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加主题--管理后台</title>
   <script type="text/javascript">
		function check(){
			var tname = document.getElementById("tname");
	
			if(tname.value == ""){
				alert("请输入主题名称！！");
				tname.focus();
				return false;
			}		
			return true;
		}
	</script>
    <link href="${pageContext.request.contextPath}/statics/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
  <div id="main">
      <div><%@include file="/jsp/newspages/console_element/top.jsp"%></div>
      <div id="opt_list"><%@include file="/jsp/newspages/console_element/left.jsp"%></div>
	  <div id="opt_area">
	    <h1 id="opt_type"> 添加主题： </h1>
	    <form action="${pageContext.request.contextPath}/TopicServlet?method=addTopic" method="post" onsubmit="return check()" >
	      <p>
	        <label> 主题名称 </label>
	        <input name="tname" type="text" class="opt_input" />
	      </p>
	      <input name="action" type="hidden" value="addtopic">
	      <input type="submit" value="提交" class="opt_sub" />
	      <input type="reset" value="重置" class="opt_sub" />
	    </form>
	  </div>
	  <%@include file="/jsp/newspages/console_element/bottom.jsp"%>
  </div>
</body>
</html>		