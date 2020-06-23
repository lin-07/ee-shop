package cn.itcast.dao;

import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void addOrder(Order order) throws SQLException;

    void addOrderItem(OrderItem orderItem) throws SQLException;

    int findRecord(User user) throws SQLException;

    List<Order> findMyOrder(User user, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;
}
