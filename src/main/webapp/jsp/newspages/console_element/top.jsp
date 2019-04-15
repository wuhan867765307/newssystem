<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="header">
  <div id="welcome">欢迎使用新闻管理系统！</div>
  <div id="nav">
    <div id="logo"><img src="${pageContext.request.contextPath }/statics/images/logo.jpg" alt="新闻中国" /></div>
    <div id="a_b01"><img src="${pageContext.request.contextPath }/statics/images/a_b01.gif" alt="" /></div>
  </div>
</div>
<div id="admin_bar">
  <div id="status">管理员：${userSession.uname} 登录  &#160;&#160;&#160;&#160; <a href="${pageContext.request.contextPath }/UserServlet?method=loginOut">login out</a></div>
  <div id="channel"> </div>
</div>
