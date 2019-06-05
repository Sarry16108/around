package com.sfy.module.store.entity;

public class HomeRecommendData {
    private String imgUrl;
    private String title;
    private String price;

    public HomeRecommendData(String imgUrl, String title, String price) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
