package cn.itcast.service.impl;

import cn.itcast.dao.CategoryDao;
import cn.itcast.dao.impl.CategoryDaoImpl;
import cn.itcast.domain.Category;
import cn.itcast.service.CategoryService;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public String findAll() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categoryList = categoryDao.findAll();
        String json = new Gson().toJson(categoryList);
        return json;
    }
}
