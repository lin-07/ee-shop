package cn.itcast.service.impl;

import cn.itcast.dao.OrderDao;
import cn.itcast.dao.impl.OrderDaoImpl;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.Page;
import cn.itcast.domain.User;
import cn.itcast.service.OrderService;
import cn.itcast.utils.DataSourceUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder(Order order) {
        OrderDao orderDao = new OrderDaoImpl();
        try{
            DataSourceUtils.startTransaction();
            orderDao.addOrder(order);
            for(OrderItem orderItem: order.getOrderItems()){
                orderDao.addOrderItem(orderItem);
            }
            DataSourceUtils.commitAndClose();
        }catch (Exception e){
            e.printStackTrace();
            DataSourceUtils.rollbackAndClose();
        }
    }

    @Override
    public Page<Order> findMyOrder(User user, int pageNumber, int pageSize) throws IllegalAccessException, SQLException, InvocationTargetException {
        OrderDao orderDao = new OrderDaoImpl();
        Page<Order> page = new Page<>(pageNumber,pageSize);
        int record = orderDao.findRecord(user);
        List<Order> data = orderDao.findMyOrder(user,page.getStartIndex(),pageSize);
        page.setData(data);
        page.setTotalRecord(record);
        return page;
    }
}
