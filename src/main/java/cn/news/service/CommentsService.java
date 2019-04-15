package cn.news.service;

import cn.news.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentsService {
    // 通过新闻id查找评论
    public List<Comment> findCommentsByNid(int nid) throws SQLException;
    // 添加评论
    public int addComment(Comment comment) throws SQLException;
    // 删除评论
    public int deleteCommentById(int cid) throws SQLException;
}
