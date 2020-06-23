package cn.itcast.dao.impl;

import cn.itcast.constant.Constant;
import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Product;
import cn.itcast.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findNewProducts() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from product order by pdate desc limit 9 ";
        Object[] params = {};
        return qr.query(sql,new BeanListHandler<Product>(Product.class),params);
    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from product where 1=1 and is_hot = ? limit 9 ";
        Object[] params = {
                Constant.PRODUCT_ISHOT_1
        };
        return qr.query(sql,new BeanListHandler<Product>(Product.class),params);
    }

    @Override
    public Product findByPid(String pid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from product p left join category c on p.cid = c.cid where p.pid=? ";
        Object[] params = {
                pid
        };
        return qr.query(sql,new BeanHandler<>(Product.class),params);
    }

    @Override
    public List<Product> findByCid(String cid,int startIndex,int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from product where 1=1 and cid = ? limit ?,? ";
        Object[] params = {
                cid,startIndex,pageSize
        };
        return qr.query(sql,new BeanListHandler<>(Product.class),params);
    }

    @Override
    public int findRecord(String cid) throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select count(*) from product where 1=1 and cid =? ";
        Object[] params = {
                cid
        };
        Object obj = qr.query(sql, new ScalarHandler(), params);
        return Integer.valueOf(obj.toString());
    }
}
