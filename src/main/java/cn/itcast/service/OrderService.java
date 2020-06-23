package cn.itcast.service;

import cn.itcast.domain.Order;
import cn.itcast.domain.Page;
import cn.itcast.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface OrderService {
    void addOrder(Order order);

    Page<Order> findMyOrder(User user, int pageNumber, int pageSize) throws IllegalAccessException, SQLException, InvocationTargetException;
}
