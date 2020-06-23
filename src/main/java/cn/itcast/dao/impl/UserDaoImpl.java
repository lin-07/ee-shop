package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findByNameAndPwd(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from user where username = ? and password = ? ";
        Object[] params = {
                user.getUsername(),user.getPassword()
        };
        return qr.query(sql,new BeanHandler<>(User.class),params);
    }

    @Override
    public User findByCode(String code) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from user where 1=1 and code = ? ";
        Object[] params = {
                code
        };
        return qr.query(sql,new BeanHandler<>(User.class),params);
    }

    @Override
    public int update(User existUser) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " update user set state=?,code=? where uid=? ";
        Object[] params = {
                existUser.getState(),existUser.getCode(),existUser.getUid()
        };
        return qr.update(sql,params);
    }

    @Override
    public void add(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = " insert into user values(?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = {
                user.getUid(),user.getUsername(),user.getPassword(),
                user.getName(),user.getEmail(),user.getTelephone(),
                user.getBirthday(),user.getSex(),user.getState(),
                user.getCode()
        };
        qr.update(sql,params);
    }
}
