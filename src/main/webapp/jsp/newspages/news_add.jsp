<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加新闻--管理后台</title>
    <link href="${pageContext.request.contextPath}/statics/css/admin.css" rel="stylesheet" type="text/css"/>

</head>

<body>

<div id="main">
    <!-- 用来保存当前的上下文路径：在js中使用 -->
    <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
    <div>
        <%@include file="/jsp/newspages/console_element/top.jsp" %>
    </div>
    <div id="opt_list">
        <%@include file="/jsp/newspages/console_element/left.jsp" %>
    </div>
    <div id="opt_area">
        <h1 id="opt_type"> 添加新闻： </h1>
        <form action="${pageContext.request.contextPath}/NewsServlet?method=newsadd" method="post" enctype="multipart/form-data">
            <p>
                <label> 主题 </label>
                <select class="topics" name="ntid">
                    <option value="-1">选择</option>
                    <%--使用ajax异步加载新闻主题--%>
                    <%--<option value='1'> 国内</option>--%>
                </select>
            </p>
            <p>
                <label> 标题 </label>
                <input name="ntitle" type="text" class="opt_input"/>
            </p>
            <p>
                <label> 作者 </label>
                <input name="nauthor" type="text" class="opt_input"/>
            </p>
            <p>
                <label> 摘要 </label>
                <textarea name="nsummary" cols="40" rows="3"></textarea>
            </p>
            <p>
                <label> 内容 </label>
                <textarea name="ncontent" cols="70" rows="10"></textarea>
            </p>
            <p>
                <label> 上传图片 </label>
                <input name="file" type="file" class="opt_input"/>
            </p>
            <input name="action" type="hidden" value="addnews"/>
            <input type="submit" value="提交" class="opt_sub"/>
            <input type="reset" value="重置" class="opt_sub"/>
        </form>
    </div>
    <%@include file="/jsp/newspages/console_element/bottom.jsp" %>
</div>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/news_add.js"></script>
</body>
</html>
  