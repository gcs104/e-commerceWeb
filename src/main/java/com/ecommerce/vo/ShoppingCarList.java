package com.whz.commerce.vo;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCarList {

    List<ShoppingCarProduct> shoppingCarProductsList;
    BigDecimal cartTotalPrice;
    //商品总价
    Integer allChecked;
    //商品总数
    private String imageHost;

    public List<ShoppingCarProduct> getCartProductVoList() {
        return shoppingCarProductsList;
    }

    public void setCartProductVoList(List<ShoppingCarProduct> cartProductVoList) {
        this.shoppingCarProductsList = cartProductVoList;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public Integer getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Integer allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        //照片地址
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        //照片地址
        this.imageHost = imageHost;
    }
}
