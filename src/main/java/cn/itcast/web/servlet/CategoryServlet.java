package cn.itcast.web.servlet;

import cn.itcast.service.CategoryService;
import cn.itcast.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/CategoryServlet"})
public class CategoryServlet extends BaseServlet {

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        try{
            CategoryService categoryService = new CategoryServiceImpl();
            String categoryJson = categoryService.findAll();
            response.getWriter().print(categoryJson);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
