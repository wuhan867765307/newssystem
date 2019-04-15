<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>添加主题--管理后台</title>
<link href="${pageContext.request.contextPath }/statics/css/admin.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- 用来保存当前的上下文路径：在js中使用 -->
	<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
	<div id="main">
		<div><%@include file="/jsp/newspages/console_element/top.jsp"%></div>
		<div id="opt_list"><%@include file="/jsp/newspages/console_element/left.jsp"%></div>
		<div id="opt_area">
			<ul class="classlist">
				<!-- 此处使用异步加载数据添加新闻列表 开始 -->
				<%-- <li>
					深足教练组：说我们买球是侮辱 朱广沪常暗中支招 
					<span> 作者： sport
							&#160;&#160;&#160;&#160; <a href='${pageContext.request.contextPath }/jsp/newspages/news_modify.jsp'>修改</a>
							&#160;&#160;&#160;&#160; <a href='javascript:;'>删除</a>
					</span>
				</li>
				<li class='space'></li> --%>
				<!-- 此处使用异步加载数据添加新闻列表 结束 -->
			</ul>
			<p align="right">
					当前页数:[<span class="currentPageNo"></span>/<span class="totalPageCount"></span>]&nbsp; 
					<a class="firstPage" href="javascript:;">首页</a>
					<a class="previousePage" href="javascript:;">上一页</a>
					<a class="nextPage" href="javascript:;">下一页</a> 
					<a class="lastPage" href="javascript:;">末页</a>
			</p>
		</div>
		<%@include file="/jsp/newspages/console_element/bottom.jsp"%>
	</div>
	<script src="${pageContext.request.contextPath }/statics/js/jquery-1.8.2.min.js"></script>
	<script src="${pageContext.request.contextPath }/statics/js/admin.js"></script>
</body>
</html>

