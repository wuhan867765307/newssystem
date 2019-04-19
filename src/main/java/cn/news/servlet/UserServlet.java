package cn.news.servlet;

import cn.news.entity.User;
import cn.news.service.UserService;
import cn.news.service.impl.UserServiceImpl;
import cn.news.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {
	private static Logger logger=Logger.getLogger(UserServlet.class);
	
	private UserService userService=new UserServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		String method=request.getParameter("method");
		PrintWriter out= response.getWriter();
		
		if("login".equals(method)) {
			String uname=request.getParameter("uname");
			String upwd=request.getParameter("upwd");
			User user=new User(uname,upwd);
			User loginedUser=null;
			try {
				loginedUser=userService.doLogin(user);
				if(loginedUser!=null) {//登录成功
					request.getSession().setAttribute(Constants.USER_SESSION, loginedUser);
					response.sendRedirect("jsp/newspages/admin.jsp");
				}else {//登录失败
					request.setAttribute("error","用户名或密码错误！");
					request.getRequestDispatcher("index.jsp").forward(request, response);	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("loginOut".equals(method)) {
			User user= (User)request.getSession().getAttribute(Constants.USER_SESSION);
			if(null!=user) {//有当前登录的用户
				request.getSession().invalidate();//会话失效
				response.sendRedirect("index.jsp");
			}
		}
		
		
	}
	
	
	
	
}
