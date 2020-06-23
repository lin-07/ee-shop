package cn.itcast.service;

import cn.itcast.domain.User;

import java.sql.SQLException;

public interface UserService {
    void add(User user) throws SQLException;

    User findByNameAndPwd(User user) throws SQLException;

    void active(String code) throws SQLException;
}
