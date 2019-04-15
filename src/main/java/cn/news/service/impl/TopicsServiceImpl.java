package cn.news.service.impl;

import cn.news.dao.impl.NewsDaoImpl;
import cn.news.dao.impl.TopicsDaoImpl;
import cn.news.service.TopicsService;
import cn.news.entity.News;
import cn.news.entity.Topic;
import cn.news.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TopicsServiceImpl implements TopicsService {

	public List<Topic> findAllTopics() throws SQLException {
		Connection conn=null;
		List<Topic> list=null;
		try {
			conn=DatabaseUtil.getConnection();
			list=new TopicsDaoImpl(conn).getAllTopics();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return list;
	}

	public int updateTopic(Topic topic) throws SQLException {
		Connection conn=null;
		int result=-1;
		try {
			conn=DatabaseUtil.getConnection();
			result=new TopicsDaoImpl(conn).updateTopic(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return result;
	}

	public Topic findTopicByName(String name) throws SQLException {
		return null;
	}

	public int addTopic(String name) throws SQLException {
		Connection conn=null;
		int result=-1;
		try {
			conn=DatabaseUtil.getConnection();
			result=new TopicsDaoImpl(conn).addTopic(name);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return result;
	}

	public int deleteTopic(int tid) throws SQLException {
		Connection conn=null;
		int result=-1;
		List<News> newsList=null;
		try {
			conn=DatabaseUtil.getConnection();
			newsList= new NewsDaoImpl(conn).getAllnewsByTID(tid);
			if(newsList!=null && newsList.size()>0){//该主题下有新闻，不能删除
				return -1;
			}else{
				result=new TopicsDaoImpl(conn).deleteTopic(tid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return result;
	}

   

}
