package cn.itcast.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lin-PC
 */
public class Cart {

    private Map<String,CartItem> cartItems = new HashMap<>();

    private Double totalPrice = 0.0;

    /**
     * 加入购物车
     * @param cartItem
     */
    public void addCart(CartItem cartItem){
        String pid = cartItem.getProduct().getPid();
        if(cartItems.containsKey(pid)){
            cartItems.get(pid).setCount(cartItems.get(pid).getCount() + cartItem.getCount());
        }else{
            cartItems.put(pid,cartItem);
        }
        totalPrice = totalPrice + cartItem.getSubPrice();
    }

    /**
     * 从购物车中移除
     * @param pid
     */
    public void removeCart(String pid){
        CartItem removeCartItem = cartItems.remove(pid);
        totalPrice = totalPrice - removeCartItem.getSubPrice();
    }

    /**
     * 清空购物车
     */
    public void cleanCart(){
        cartItems.clear();
        totalPrice = 0.0;
    }


    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
