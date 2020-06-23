package cn.itcast.web.servlet;

import cn.itcast.domain.Page;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ProductServlet"})
public class ProductServlet extends BaseServlet {

    /**
     * 根据分类查询商品带分页
     * @param request
     * @param response
     * @return
     */
    public String findByCid(HttpServletRequest request, HttpServletResponse response){
        try{
            String cid = request.getParameter("cid");
            String pageNumberStr = request.getParameter("pageNumber");
            int pageNumber = 1;
            try{
                pageNumber = Integer.valueOf(pageNumberStr);
                if(pageNumber < 1){
                    pageNumber = 1;
                }
            }catch (Exception e){
                // NOOP
            }
            // 写死pageSize;
            int pageSize = 2;
            ProductService productService = new ProductServiceImpl();
            Page<Product> page = productService.findByCid(cid,pageNumber,pageSize);
            request.setAttribute("page",page);
            request.setAttribute("cid",cid);
            return "/jsp/product_list.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 商品详情
     * @param request
     * @param response
     * @return
     */
    public String findByPid(HttpServletRequest request, HttpServletResponse response){
        try{
            String pid = request.getParameter("pid");
            ProductService productService = new ProductServiceImpl();
            Product product = productService.findByPid(pid);
            request.setAttribute("product",product);
            return "/jsp/product_info.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
