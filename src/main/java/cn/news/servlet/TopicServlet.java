package cn.news.servlet;

import cn.news.entity.Topic;
import cn.news.service.TopicsService;
import cn.news.service.impl.TopicsServiceImpl;
import com.alibaba.fastjson.JSONArray;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class TopicServlet
 */
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static Logger logger=Logger.getLogger(TopicServlet.class);
       
	private TopicsService topicService=new TopicsServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out= response.getWriter();
		String path=request.getContextPath();
		String method=request.getParameter("method");
		if("getAllTopic".equals(method)) {//获取新闻主题列表
			List<Topic> list=null;
			try {
				list= topicService.findAllTopics();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.print(JSONArray.toJSONString(list));
		}else if("getTopicByTid".equals(method)){
			String tid=request.getParameter("tid");
			String tname=request.getParameter("tname");
			Topic topic=new Topic();
			topic.setTid(Integer.parseInt(tid));
			topic.setTname(tname);
			logger.debug(topic.getTid()+"-"+topic.getTname());
			request.setAttribute("topic",topic);
			request.getRequestDispatcher("/jsp/newspages/topic_modify.jsp").forward(request,response);
		}else if("modifyTopicByTid".equals(method)){
			String tid=request.getParameter("tid");
			String tname=request.getParameter("tname");
			Topic topic=new Topic();
			topic.setTid(Integer.parseInt(tid));
			topic.setTname(tname);
			try {
				int result=topicService.updateTopic(topic);
				if(result>0){
					response.sendRedirect(path+"/jsp/newspages/topic_list.jsp");
				}else{
					response.sendRedirect(path+"/jsp/newspages/topic_modify.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("deleteTopicByTid".equals(method)){
			String tid=request.getParameter("tid");
			String flag="false";
			try {
				int result=topicService.deleteTopic(Integer.parseInt(tid));
				if(result>0){
					flag="true";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				flag="error";
			}
			out.print("{\"flag\":\""+flag+"\"}");
		}else if("addTopic".equals(method)){
			String tname=request.getParameter("tname");
			int result=-1;
			try {
				result = topicService.addTopic(tname);
				if(result>0){
					response.sendRedirect(path+"/jsp/newspages/topic_list.jsp");
				}else{
					response.sendRedirect(path+"/jsp/newspages/topic_add.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		out.flush();
		out.close();
		
		
		
		
		
		
	}

}
