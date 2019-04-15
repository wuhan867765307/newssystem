package cn.news.service;

import java.sql.SQLException;

import cn.news.entity.User;

public interface UserService {
    public User doLogin(User user) throws SQLException;
}
