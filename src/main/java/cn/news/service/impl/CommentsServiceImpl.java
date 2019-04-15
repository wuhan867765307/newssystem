package cn.news.service.impl;

import cn.news.dao.impl.CommentsDaoImpl;
import cn.news.service.CommentsService;
import cn.news.entity.Comment;
import cn.news.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentsServiceImpl implements CommentsService {

	public List<Comment> findCommentsByNid(int nid) throws SQLException {
		Connection conn=null;
		List<Comment> list=null;
		try {
			conn=DatabaseUtil.getConnection();
			list = new CommentsDaoImpl(conn).getCommentsByNid(nid);

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(conn,null,null);
		}
		return list;
	}

	public int addComment(Comment comment) throws SQLException {
		return 0;
	}

	public int deleteCommentById(int cid) throws SQLException {
		return 0;
	}

}
