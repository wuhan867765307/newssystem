package cn.news.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import cn.news.dao.impl.UserDaoImpl;
import cn.news.service.UserService;
import cn.news.entity.User;
import cn.news.util.DatabaseUtil;

public class UserServiceImpl implements UserService {

    public User doLogin(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();

            return new UserDaoImpl(conn).findUser(user.getUname(),
                    user.getUpwd());
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DatabaseUtil.closeAll(conn, null, null);
        }
    }

}
