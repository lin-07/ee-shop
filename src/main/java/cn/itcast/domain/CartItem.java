package cn.itcast.domain;

/**
 * @author lin-PC
 */
public class CartItem {


    public CartItem() {
    }

    public CartItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    private Product product;

    private int count = 0;

    private Double subPrice = 0.0;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getSubPrice() {
        subPrice = count * product.getShop_price();
        return subPrice;
    }
}
