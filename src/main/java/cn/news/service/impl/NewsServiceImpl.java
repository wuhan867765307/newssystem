package cn.news.service.impl;

import cn.news.dao.NewsDao;
import cn.news.dao.impl.CommentsDaoImpl;
import cn.news.dao.impl.NewsDaoImpl;
import cn.news.entity.Comment;
import cn.news.entity.News;
import cn.news.service.NewsService;
import cn.news.util.DatabaseUtil;
import cn.news.util.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class NewsServiceImpl implements NewsService {

	public List<News> findAllNews() throws SQLException {
		return null;
	}

	public List<News> findAllNewsByTid(int tid) throws SQLException {
		return null;
	}

	public List<News> findAllNewsByTname(String tname) throws SQLException {
		return null;
	}

	public List<News> findLatestNewsByTid(int tid, int limit) throws SQLException {
		return null;
	}

	public List<List<News>> findLatestNewsByTid(Map<Integer, Integer> topicsMap) throws SQLException {
		return null;
	}

	public News findNewsByNid(int nid) throws SQLException {
		Connection conn=null;
		News news=null;
		try {
			conn=DatabaseUtil.getConnection();
			news=new NewsDaoImpl(conn).getNewsByNID(nid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return news;
	}

	public int deleteNews(int nid) throws SQLException {
		Connection conn=null;
		int result=-1;
		try {
			conn=DatabaseUtil.getConnection();
			List<Comment> list=new CommentsDaoImpl(conn).getCommentsByNid(nid);
			if(list!=null) {
				if(list.size()==0) {//没有评论才能删掉
					result=new NewsDaoImpl(conn).deleteNews(nid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return result;
	}

	public int addNews(News news) throws SQLException {
		Connection conn=null;
		int result=-1;
		try {
			conn=DatabaseUtil.getConnection();
			result=new NewsDaoImpl(conn).addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return result;
	}

	public int modifyNews(News news) throws SQLException {
		Connection conn=null;
		int result=-1;
		try {
			conn=DatabaseUtil.getConnection();
			result=new NewsDaoImpl(conn).updateNews(news);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return result;
	}

	@Override
	public int findNewsCount() throws SQLException {
		Connection conn=null;
		int count=-1;
		try {
			conn=DatabaseUtil.getConnection();
			count=new NewsDaoImpl(conn).getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		return count;
	}

	public void findPageNews(Page<News> pageObj) throws SQLException {
		Connection conn=null;
		List<News> pageList=null;
		NewsDao newsDao=null;
		try {
			conn=DatabaseUtil.getConnection();
			newsDao=new NewsDaoImpl(conn);
			pageList=newsDao.getPageNewsList(pageObj.getCurrentPageNo(), pageObj.getPageSize());
			pageObj.setPageList(pageList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn, null, null);
		}
		
	}

}
