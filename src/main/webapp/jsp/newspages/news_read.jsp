<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>添加主题--管理后台</title>
    <link href="${pageContext.request.contextPath }/statics/css/admin.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<!-- 用来保存当前的上下文路径：在js中使用 -->
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<div id="main">
    <div>
        <%@include file="/jsp/newspages/console_element/top.jsp" %>
    </div>
    <div id="opt_area">
        <div class="class_type">
            <img src="${pageContext.request.contextPath }/statics/images/class_type.gif" alt="新闻中心"/></div>
    </div>
    <div class="content">
        <ul class="classlist">
            <table align="center" width="100%">
                <tr width="100%">
                    <td colspan="6" align="center"><h2>${news.ntitle}</h2></td>
                </tr>
                <tr>
                    <td colspan="6">
                        <hr/>
                    </td>
                </tr>
                <tr>
                    <td align="center">${news.ncreatedate}</td>
                    <td align="left">${news.nauthor}</td>
                </tr>
                <tr>
                    <td colspan="6" align="center"></td>
                </tr>
                <tr>
                    <td colspan="6"> 　${news.ncontent}</td>
                </tr>
                <tr>
                    <td colspan="6">
                        <hr/>
                    </td>
                </tr>
            </table>
        </ul>
        <%--评论部分--%>
        <ul class="classlist">
            <table width="100%" align="center">
                <c:if test="${news.comments==null}">
                    <tr>
                        <td colspan="6"> 暂无评论！</td>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <hr/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${news.comments!=null}">
                    <c:forEach var="comment" items="${news.comments}">
                        <tr>
                            <td> 留言人：</td>
                            <td> ${comment.cauthor}</td>
                            <td> IP：</td>
                            <td> ${comment.cip}</td>
                            <td> 留言时间：</td>
                            <td> ${comment.cdate}</td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <hr/>
                            </td>
                        </tr>
                    </c:forEach>

                </c:if>
            </table>
        </ul>
        <ul class="classlist">
            <form action="#" method="post" onsubmit="return check()">
                <table width="100%" align="center">
                    <tr>
                        <td> 评 论</td>
                    </tr>
                    <tr>
                        <td> 用户名：</td>
                        <td><input id="cauthor" name="cauthor" value="这家伙很懒什么也没留下"/>
                            IP：
                            <input name="cip" value="127.0.0.1" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><textarea name="ccontent" cols="150" rows="10" style="resize: none"></textarea>
                        </td>
                    </tr>
                    <td><input name="submit" value="发  表" type="submit"/>
                    </td>
                </table>
            </form>
        </ul>
    </div>
</div>
<%@include file="/jsp/newspages/console_element/bottom.jsp" %>
<script src="${pageContext.request.contextPath }/statics/js/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/admin.js"></script>
</body>
</html>

