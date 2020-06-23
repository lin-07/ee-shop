package cn.itcast.web.servlet;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/IndexServlet"})
public class IndexServlet extends BaseServlet {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try{
            // 获取最新商品和最热商品
            ProductService productService = new ProductServiceImpl();
            List<Product> newProducts = productService.findNewProducts();
            List<Product> hotProducts = productService.findHotProducts();
            request.setAttribute("newProducts",newProducts);
            request.setAttribute("hotProducts",hotProducts);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/jsp/index.jsp";
    }
}
