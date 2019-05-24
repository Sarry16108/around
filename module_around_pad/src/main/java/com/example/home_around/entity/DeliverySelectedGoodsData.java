package com.example.home_around.entity;

public class DeliverySelectedGoodsData {
    private String imgUrl;
    private String title;
    private String extInfo;
    private String price;
    private int count;

    public DeliverySelectedGoodsData(String imgUrl, String title, String extInfo, String price, int count) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.extInfo = extInfo;
        this.price = price;
        this.count = count;
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
}
