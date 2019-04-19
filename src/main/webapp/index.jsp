<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻中国</title>
<link href="${pageContext.request.contextPath }/statics/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- 用来保存当前的上下文路径：在js中使用 -->
<input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
<div id="header">
  <div id="top_login">
  	<form action="${pageContext.request.contextPath }/UserServlet?method=login" method="post" >
	  	<label> 登录名 </label>
	    <input type="text" id="uname" name="uname" value="" class="login_input" />
	    <label> 密&#160;&#160;码 </label>
	    <input type="password" id="upwd" name="upwd" value="" class="login_input" />
	    <input type="submit" class="login_sub" value="登录 "/>
	    <label id="error">${error} </label>
	    <img src="${pageContext.request.contextPath }/statics/images/friend_logo.gif" alt="Google" id="friend_logo" />
    </form>
  </div>
    
  <div id="nav">
    <div id="logo"> <img src="${pageContext.request.contextPath }/statics/images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="${pageContext.request.contextPath }/statics/images/a_b01.gif" alt="" /> </div>
  </div>
</div>
<div id="container">
  <div class="sidebar">
    <h1> <img src="${pageContext.request.contextPath }/statics/images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>
        <li> <a href='#'><b> 景区，如何不再依靠门票收入 </b></a> </li>
        <li> <a href='#'><b> 高考期间中东部地区将现大范围降雨 </b></a> </li>
        <li> <a href='#'><b> 山西离柳焦煤集团井下人行车发生事故6人死亡 </b></a> </li>
      </ul>
    </div>
    <h1> <img src="${pageContext.request.contextPath }/statics/images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul>
        <li> <a href='#'><b> 习近平在墨国会发表演讲:朋友要老 好酒要陈 </b></a> </li>
        <li> <a href='#'><b> 普京逮捕铁腕市长展示肌肉向世人表明克宫仍大权在握</b></a> </li>
        <li> <a href='#'><b> 潘基文祝贺赖斯被任命为美国国家安全顾问 </b></a> </li>
        <li> <a href='#'><b> 与基地有关组织宣称对巴格达连环爆炸负责 </b></a> </li>
      </ul>
    </div>
    <%--<h1> <img src="${pageContext.request.contextPath }/statics/images/title_3.gif" alt="娱乐新闻" /> </h1>--%>
    <%--<div class="side_list">--%>
      <%--<ul>--%>
        <%--<li> <a href='#'><b> "星跳水立方"决战临近 陈楚生被华谊要求进前三 </b></a> </li>--%>
        <%--<li> <a href='#'><b> 《新恋爱时代》登东方卫视 《非诚》元素遭删除 </b></a> </li>--%>
        <%--<li> <a href='#'><b> 《海角七号》导演新片开机 吴宇森等出席 </b></a> </li>--%>
        <%--<li> <a href='#'><b> 《致命黑兰》佐伊坐拥7种武器 暴力登陆全国院线 </b></a> </li>--%>
      <%--</ul>--%>
    <%--</div>--%>
  </div>
  <div class="main">
    <div class="class_type"> <img src="${pageContext.request.contextPath }/statics/images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
        <li class='class_month'>
          <%--异步加载新闻主题--%>
        </li>
        <%--<li class='class_month'> <a href='#'><b> 房产 </b></a> <a href='#'><b> 家居 </b></a> <a href='#'><b> 旅游 </b></a> <a href='#'><b> 文化 </b></a> <a href='#'><b> 其他 </b></a> </li>--%>
      </ul>
      <ul class="classlist">
          <%--异步加载所有新闻--%>
        <%--<li><a href='news_read.jsp'> 深足教练组：说我们买球是侮辱 朱广沪常暗中支招 </a><span> 2013-06-06 01:03:51.0 </span></li>--%>
        <%--<li><a href='#'> 习近平在墨国会发表演讲:朋友要老 好酒要陈 </a><span> 2013-06-06 01:03:19.0 </span></li>--%>
        <%--<li><a href='#'> 《致命黑兰》佐伊坐拥7种武器 暴力登陆全国院线 </a><span> 2013-06-06 01:02:56.0 </span></li>--%>
        <%--<li><a href='#'> "星跳水立方"决战临近 陈楚生被华谊要求进前三 </a><span> 2013-06-06 01:02:17.0 </span></li>--%>
        <%--<li><a href='#'> 步行者崩盘主要原因在哪 解决3问题能发起更强挑战--%>
 <%--</a><span> 2013-06-06 01:01:47.0 </span></li>--%>
        <%--<li class='space'></li>--%>
      </ul>
        <p align="right">
            当前页数:[<span class="currentPageNo"></span>/<span class="totalPageCount"></span>]&nbsp;
            <a class="firstPage" href="javascript:;">首页</a>
            <a class="previousePage" href="javascript:;">上一页</a>
            <a class="nextPage" href="javascript:;">下一页</a>
            <a class="lastPage" href="javascript:;">末页</a>
        </p>
    </div>
    <div class="picnews">
      <ul>
        <li> <a href="#"><img src="${pageContext.request.contextPath }/statics/images/Picture1.jpg" width="249" alt="" /> </a><a href="#">幻想中穿越时空</a> </li>
        <li> <a href="#"><img src="${pageContext.request.contextPath }/statics/images/Picture2.jpg" width="249" alt="" /> </a><a href="#">国庆多变的发型</a> </li>
        <li> <a href="#"><img src="${pageContext.request.contextPath }/statics/images/Picture3.jpg" width="249" alt="" /> </a><a href="#">新技术照亮都市</a> </li>
        <%--<li> <a href="#"><img src="${pageContext.request.contextPath }/statics/images/Picture4.jpg" width="249" alt="" /> </a><a href="#">群星闪耀红地毯</a> </li>--%>
      </ul>
    </div>
  </div>
</div> 
<%@include file="/jsp/index-elements/index_bottom.jsp" %> 

<script src="${pageContext.request.contextPath }/statics/js/jquery-1.8.2.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/index.js"></script>
</body>
</html>
	