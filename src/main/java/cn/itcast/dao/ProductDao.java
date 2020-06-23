package cn.itcast.dao;

import cn.itcast.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findNewProducts() throws SQLException;

    List<Product> findHotProducts() throws SQLException;

    Product findByPid(String pid) throws SQLException;

    List<Product> findByCid(String cid,int startIndex,int pageSize) throws SQLException;

    int findRecord(String cid) throws SQLException;
}
