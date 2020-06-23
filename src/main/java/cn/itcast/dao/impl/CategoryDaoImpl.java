package cn.itcast.dao.impl;

import cn.itcast.dao.CategoryDao;
import cn.itcast.domain.Category;
import cn.itcast.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() throws SQLException {
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = " select * from category where 1=1 ";
        Object[] params = {};
        return qr.query(sql,new BeanListHandler<Category>(Category.class),params);
    }
}
