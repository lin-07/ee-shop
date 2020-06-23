package cn.itcast.web.servlet;

import cn.itcast.domain.Cart;
import cn.itcast.domain.CartItem;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/CartServlet"})
public class CartServlet extends BaseServlet {


    /**
     * 清空购物车
     * @param request
     * @param response
     * @return
     */
    public String clearCart(HttpServletRequest request, HttpServletResponse response){
        try{
            Cart cart = getCart(request);
            cart.cleanCart();
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移除购物车
     * @param request
     * @param response
     * @return
     */
    public String removeCart(HttpServletRequest request, HttpServletResponse response){
        try{
            String pid = request.getParameter("pid");
            getCart(request).removeCart(pid);
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加入购物车
     * @param request
     * @param response
     * @return
     */
    public String addCart(HttpServletRequest request, HttpServletResponse response){
        try {
            String pid = request.getParameter("pid");
            String count = request.getParameter("count");
            ProductService productService = new ProductServiceImpl();
            Product product = productService.findByPid(pid);
            CartItem cartItem = new CartItem(product,Integer.valueOf(count));
            Cart cart = getCart(request);
            cart.addCart(cartItem);
            // 此时可以不用再往session中set购物车了 因为是引用传递
            response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Cart getCart(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        return cart;
    }
}
