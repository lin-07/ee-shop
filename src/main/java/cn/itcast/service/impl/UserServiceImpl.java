package cn.itcast.service.impl;

import cn.itcast.constant.Constant;
import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.MailUtils;

import java.sql.SQLException;

/**
 * @author lin-PC
 */
public class UserServiceImpl implements UserService {

    private static final UserDao userDao = new UserDaoImpl();

    @Override
    public void add(User user) throws SQLException {
        userDao.add(user);
        // 发送激活邮件
        String url = "http://localhost:8080/ee_shop/UserServlet?method=active&code=" +user.getCode();
        String msg = "<h1>来自购物天堂STORE的激活邮件！</h1><h3><a href='"+url+"'>激活请点击以下链接！</a></h3>";
        MailUtils.send(user.getEmail(),msg);
    }

    @Override
    public User findByNameAndPwd(User user) throws SQLException {
        return userDao.findByNameAndPwd(user);
    }

    @Override
    public void active(String code) throws SQLException {
        User existUser = userDao.findByCode(code);
        if(existUser == null){
            throw new RuntimeException("用户激活码无效，请重试");
        }
        existUser.setCode(null);
        existUser.setState(Constant.USER_STATE_1);
        int row = userDao.update(existUser);
        if(row != 1){
            throw new RuntimeException("出现未知异常");
        }
    }
}
