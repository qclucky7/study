package com.qingchen.study.beancopy;

/**
 * @ClassName Order
 * @description:
 * @author: WangChen
 * @create: 2020-06-26 19:51
 **/
public class OrderVO{

    private Long price;

    private String address;

    private String userId;

    private String phone;

    private GoodsVO goods;

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "price=" + price +
                ", address='" + address + '\'' +
                ", userId='" + userId + '\'' +
                ", phone=" + phone +
                ", goods=" + goods +
                '}';
    }
}
