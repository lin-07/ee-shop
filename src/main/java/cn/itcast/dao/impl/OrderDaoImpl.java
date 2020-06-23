package cn.itcast.dao.impl;

import cn.itcast.dao.OrderDao;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.Product;
import cn.itcast.domain.User;
import cn.itcast.utils.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void addOrder(Order order) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = " insert into orders values(?,?,?,?,?,?,?,?) ";
        Object[] params = {
                order.getOid(),order.getOrdertime(),order.getTotal(),
                order.getState(),order.getAddress(),order.getName(),
                order.getTelephone(),order.getUser().getUid()
        };
        qr.update(DataSourceUtils.getConnection(),sql,params);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) throws SQLException {
        QueryRunner qr = new QueryRunner();
        String sql = " insert into orderitem values(?,?,?,?,?) ";
        Object[] params = {
                orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),
                orderItem.getProduct().getPid(),orderItem.getOrder().getOid()
        };
        qr.update(DataSourceUtils.getConnection(),sql,params);
    }

    @Override
    public int findRecord(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select count(*) from orders o where o.uid = ? ";
        Object[] params = {
          user.getUid()
        };
        Object obj = qr.query(sql, new ScalarHandler(), params);
        return Integer.valueOf(obj.toString());
    }

    @Override
    public List<Order> findMyOrder(User user, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from orders where 1=1 and uid=? order by ordertime desc limit ?,? ";
        Object[] params = {
                user.getUid(),startIndex,pageSize
        };
        List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), params);
        sql = " select * from orderitem o left join product p on o.pid = p.pid where o.oid=? ";
        for(Order order:list){
            List<Map<String, Object>> query = qr.query(sql, new MapListHandler(), order.getOid());
            for (Map<String, Object> map :query){
                Product product = new Product();
                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(product,map);
                BeanUtils.populate(orderItem,map);
                orderItem.setProduct(product);
                order.getOrderItems().add(orderItem);
            }
        }
        return list;
    }
}
