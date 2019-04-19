<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>新闻中国</title>
    <link href="${pageContext.request.contextPath }/statics/css/admin.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath }" id="path"/>
<div id="main">
    <div>
        <%@ include file="/jsp/newspages/console_element/top.jsp" %>
    </div>
    <div id="mainContent">
        <div id="opt_list">
            <%@ include file="/jsp/newspages/console_element/left.jsp" %>
        </div>

        <div id="opt_area">
            <h1 id="opt_type"> 修改新闻： </h1>
            <form action="${pageContext.request.contextPath}/NewsServlet?method=newsmodify" method="post" enctype="multipart/form-data">
                <p>
                    <label> 主题 </label>
                    <select name="ntid" class="topics">
                        <c:forEach var="topic" items="${topicList }">
                            <option value=${topic.tid } <c:if test="${topic.tid==news.ntid }">selected</c:if>> ${topic.tname }</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="nid" value="${news.nid}"/>
                </p>
                <p>
                    <label> 标题 </label>
                    <input name="ntitle" type="text" class="opt_input" value="${news.ntitle}"/>
                </p>
                <p>
                    <label> 作者 </label>
                    <input name="nauthor" type="text" class="opt_input" value="${news.nauthor}"/>
                </p>
                <p>
                    <label> 摘要 </label>
                    <textarea name="nsummary" cols="40" rows="3">${news.nsummary}</textarea>
                </p>
                <p>
                    <label> 内容 </label>
                    <textarea name="ncontent" cols="70" rows="10">${news.ncontent}</textarea>
                </p>
                <p>
                    <label> 上传图片 </label>
                    <input name="file" type="file" class="opt_input" value=""/>
                    <input type="hidden" name="hiddenPicPath" value="${news.npicpath}"/>
                    <%--<img src="${pageContext.request.contextPath }/upload/${news.npicpath}" style="width: 200px;" name="picimg"/>--%>
                </p>
                <input type="submit" value="提交" class="opt_sub"/>
                <input type="reset" value="重置" class="opt_sub"/>
            </form>
            <p></p>
            <h1 id="opt_type_title">
                修改新闻评论：
            </h1>
            <table width="100%" align="left">
                <tr>
                    <td colspan="6">
                        <hr/>
                    </td>
                </tr>
                <c:if test="${news.comments!=null}">
                    <c:forEach var="comment" items="${news.comments}">
                        <tr>
                            <td> 留言人：</td>
                            <td>${comment.cauthor}</td>
                            <td> IP：</td>
                            <td>${comment.cip}</td>
                            <td> 留言时间：</td>
                            <td>${comment.cdate}</td>
                            <td><a href="#">删除</a></td>
                        </tr>
                        <tr>
                            <td colspan="6">${comment.ccontent}</td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <hr/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${news.comments==null}">
                    <tr>
                        <td colspan="6">
                            暂无评论
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <hr/>
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
    <div>
        <%@ include file="/jsp/newspages/console_element/bottom.jsp" %>
    </div>
</div>
</body>
</html>	