package com.sfy.module.store.entity;

public class ConfirmOrderData {
    private String shopName;
    private String shopImgUrl;
    private String delivery;


    private String imgUrl;
    private String title;
    private String extInfo;
    private String price;
    private int count;
    private boolean isFirst = false;
    private boolean isLast = false;

    public ConfirmOrderData(String imgUrl, String title, String extInfo, String price, int count, boolean isLast) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.extInfo = extInfo;
        this.price = price;
        this.count = count;
        this.isLast = isLast;
    }

    public void setShop(String shopName, String shopImgUrl, String delivery, boolean isFirst) {
        this.shopName = shopName;
        this.shopImgUrl = shopImgUrl;
        this.delivery = delivery;
        this.isFirst = isFirst;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public String getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isLast() {
        return isLast;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopImgUrl() {
        return shopImgUrl;
    }

    public String getDelivery() {
        return delivery;
    }
}
