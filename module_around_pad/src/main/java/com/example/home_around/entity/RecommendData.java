package com.example.home_around.entity;

public class RecommendData extends HomeBaseItemData {
    private int id;
    private String imgUrl;
    private String title;
    private String extInfo;
    private String price;

    public RecommendData(String imgUrl, String title, String extInfo, String price, int spanCount) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.extInfo = extInfo;
        this.price = price;
        this.spanCount = spanCount;
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

    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return TYPE_RECOMMEND;
    }
}
