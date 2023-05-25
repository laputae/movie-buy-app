package com.itheima.bean;

/**
 * 店铺类
 */
public class Business extends User{
    private String shopName;
    private String address;

    public Business(){

    }
    public Business(String loginName, String userName, String password, char sex, String phone, double money, String shopName, String address) {
        super(loginName, userName, password, sex, phone, money);
        this.shopName = shopName;
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
