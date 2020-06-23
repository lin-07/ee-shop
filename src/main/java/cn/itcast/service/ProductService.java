package cn.itcast.service;

import cn.itcast.domain.Page;
import cn.itcast.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findNewProducts() throws SQLException;

    List<Product> findHotProducts() throws SQLException;

    Product findByPid(String pid) throws SQLException;

    Page<Product> findByCid(String cid, int pageNumber, int pageSize) throws SQLException;
}
