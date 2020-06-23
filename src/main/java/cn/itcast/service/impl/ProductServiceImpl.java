package cn.itcast.service.impl;

import cn.itcast.dao.ProductDao;
import cn.itcast.dao.impl.ProductDaoImpl;
import cn.itcast.domain.Page;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findNewProducts() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findNewProducts();
    }

    // ctrl + alt + 左键 直接到方法实现类中
    @Override
    public List<Product> findHotProducts() throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findHotProducts();
    }

    @Override
    public Product findByPid(String pid) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        return productDao.findByPid(pid);
    }

    @Override
    public Page<Product> findByCid(String cid, int pageNumber, int pageSize) throws SQLException {
        ProductDao productDao = new ProductDaoImpl();
        Page<Product> page = new Page<>(pageNumber,pageSize);
        List<Product> data = productDao.findByCid(cid, page.getStartIndex(), page.getPageSize());
        int totalRecord = productDao.findRecord(cid);
        page.setTotalRecord(totalRecord);
        page.setData(data);
        return page;
    }
}
