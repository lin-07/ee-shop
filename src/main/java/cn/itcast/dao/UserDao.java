package cn.itcast.dao;

import cn.itcast.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void add(User user) throws SQLException;

    User findByNameAndPwd(User user) throws SQLException;

    User findByCode(String code) throws SQLException;

    int update(User existUser) throws SQLException;
}
