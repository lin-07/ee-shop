package cn.itcast.web.servlet;

import cn.itcast.constant.Constant;
import cn.itcast.domain.*;
import cn.itcast.service.OrderService;
import cn.itcast.service.impl.OrderServiceImpl;
import cn.itcast.utils.UUIDUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@WebServlet(urlPatterns = {"/OrderServlet"})
public class OrderServlet extends BaseServlet {

    public String findMyOrder(HttpServletRequest request, HttpServletResponse response){
        try{
            User user = (User) request.getSession().getAttribute("user");
            if(user == null){
                request.setAttribute("msg","先登录才能查看订单！");
                return "/msg.jsp";
            }
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
            // 写死pageSize
            int pageSize = 2;
            OrderService orderService = new OrderServiceImpl();
            Page<Order> page = orderService.findMyOrder(user,pageNumber,pageSize);
            request.setAttribute("page",page);
            return "/jsp/order_list.jsp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提交订单
     * @param request
     * @param response
     * @return
     */
    public String addOrder(HttpServletRequest request, HttpServletResponse response){
        try{
            User user = (User) request.getSession().getAttribute("user");
            if(user == null){
                request.setAttribute("msg","请您先去登录");
                return "/msg.jsp";
            }
            Cart cart = getCart(request);
            Order order = new Order();
            order.setOid(UUIDUtils.getId());
            order.setOrdertime(new Date());
            order.setTotal(cart.getTotalPrice());
            order.setState(Constant.ORDER_STATE_0);
            order.setUser(user);
            for(Map.Entry<String,CartItem> cartItemEntry : cart.getCartItems().entrySet()){
                CartItem cartItem = cartItemEntry.getValue();
                OrderItem orderItem = new OrderItem();
                orderItem.setItemid(UUIDUtils.getId());
                orderItem.setCount(cartItem.getCount());
                orderItem.setSubtotal(cartItem.getSubPrice());
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setOrder(order);
                order.getOrderItems().add(orderItem);
            }
            OrderService orderService = new OrderServiceImpl();
            orderService.addOrder(order);
            request.setAttribute("order",order);
            return "/jsp/order_info.jsp";
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
