package com.example.home_around.entity;

public class MyOrderData {
    private int     type;
    private String imgUrl;
    private String merchantName;
    private String title;
    private String extInfo;
    private String price;
    private String totalPrice;
    private int count;

    public MyOrderData(String merchantName, String imgUrl, String title, String extInfo, String price, String totalPrice, int count, int type) {
        this.merchantName = merchantName;
        this.imgUrl = imgUrl;
        this.title = title;
        this.extInfo = extInfo;
        this.price = price;
        this.count = count;
        this.type = type;
        this.totalPrice = totalPrice;
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

    public int getType() {
        return type;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
